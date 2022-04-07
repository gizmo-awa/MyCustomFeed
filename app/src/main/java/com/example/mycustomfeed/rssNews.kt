package com.example.mycustomfeed

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

class FeedEntry {
    var title: String = ""
    var link: String = ""
    var source: String = ""

    override fun toString(): String {
        return """
            title = $title
            link = $link
            source = $source
        """.trimIndent()
    }
}

class rssNews : AppCompatActivity() {
    private val TAG = "rssNews"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_news)

        val recyclerView: RecyclerView = findViewById(R.id.xmlRecyclerView)

        Log.d(TAG, "onCreate")

        val downloadData = DownloadData(this, recyclerView)
        downloadData.execute("https://news.google.com/rss?hl=es-419&gl=MX&ceid=MX:es-419")
        Log.d(TAG, "onCreate DONE")
    }

    companion object {
        private class DownloadData(context: Context, recyclerView: RecyclerView) : AsyncTask<String, Void, String>() {
            private val TAG = "DownloadData"

            var localContext: Context by Delegates.notNull()
            var localRecyclerView: RecyclerView by Delegates.notNull()
            init {
                localContext = context
                localRecyclerView = recyclerView
            }

            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground")
                Log.d(TAG, downloadXML(url[0]))
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.e(TAG, "doInBackground: failed")
                }
                Log.d(TAG, rssFeed)
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute")
                val parsedApplication = ParseApplications()
                parsedApplication.parse(result)
                Log.d(TAG, result)
                Log.d(TAG, parsedApplication.parse(result).toString())

                val adapter: ApplicationsAdapter = ApplicationsAdapter(localContext, parsedApplication.applications)
                localRecyclerView.adapter = adapter
                localRecyclerView.layoutManager = LinearLayoutManager(localContext)
            }

            private fun downloadXML(urlPath: String?): String {
                try {
                    return URL(urlPath).readText()
                } catch (e: Exception) {
                    val errorMessage: String = when (e) {
                        is MalformedURLException -> "downloadXML: Invalid URL ${e.message}"
                        is IOException -> "downloadXML: IOException reading data ${e.message}"
                        else -> "downloadXML: Unknown Error ${e.message}"
                    }
                    Log.e(TAG, errorMessage)
                }
                return ""
            }
        }
    }
}