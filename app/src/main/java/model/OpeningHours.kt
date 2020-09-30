package model

class OpeningHours {

    private var open_now = false

    fun setOpen_now(open_now: Boolean) {
        this.open_now = open_now
    }

    fun getOpen_now(): Boolean {
        return open_now
    }
}