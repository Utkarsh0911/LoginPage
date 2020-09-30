package model

class MyPlaces {
    private var html_attributions: List<String>? = null

    private var next_page_token: String? = null

    private var results: List<Results>? = null

    private var status: String? = null

    fun setHtml_attributions(html_attributions: List<String>?) {
        this.html_attributions = html_attributions
    }

    fun getHtml_attributions(): List<String>? {
        return html_attributions
    }

    fun setNext_page_token(next_page_token: String?) {
        this.next_page_token = next_page_token
    }

    fun getNext_page_token(): String? {
        return next_page_token
    }

    fun setResults(results: List<Results>?) {
        this.results = results
    }

    fun getResults(): List<Results>? {
        return results
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getStatus(): String? {
        return status
    }

}