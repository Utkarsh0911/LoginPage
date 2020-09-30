package model

class PlusCode {
    private var compound_code: String? = null

    private var global_code: String? = null

    fun setCompound_code(compound_code: String?) {
        this.compound_code = compound_code
    }

    fun getCompound_code(): String? {
        return compound_code
    }

    fun setGlobal_code(global_code: String?) {
        this.global_code = global_code
    }

    fun getGlobal_code(): String? {
        return global_code
    }
}