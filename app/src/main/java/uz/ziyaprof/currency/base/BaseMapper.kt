package uz.ziyaprof.currency.base

interface BaseMapper<DTO, UI_MODEL> {
    fun toUIModel(dto: DTO): UI_MODEL
}