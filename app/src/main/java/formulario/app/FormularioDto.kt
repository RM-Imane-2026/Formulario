package formulario.app

data class FormularioDto (
    val titulo:String,
    val descripcion:String,
    val categoria:String,
    val prioridad:Int,
    val email:String,
    val fecha:String
)
