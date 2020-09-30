package model

class Photos {

    private var height = 0

    private var html_attributions: List<String>? = null

    private var photo_reference: String? = null

    private var width = 0

    fun setHeight(height: Int) {
        this.height = height
    }

    fun getHeight(): Int {
        return height
    }

    fun setHtml_attributions(html_attributions: List<String>?) {
        this.html_attributions = html_attributions
    }

    fun getHtml_attributions(): List<String>? {
        return html_attributions
    }

    fun setPhoto_reference(photo_reference: String?) {
        this.photo_reference = photo_reference
    }

    fun getPhoto_reference(): String? {
        return photo_reference
    }

    fun setWidth(width: Int) {
        this.width = width
    }

    fun getWidth(): Int {
        return width
    }
}