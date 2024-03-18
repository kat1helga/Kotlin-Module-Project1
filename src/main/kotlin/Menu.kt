import java.util.Scanner

object Menu {
    private val scanner = Scanner(System.`in`)

    fun show(title: String, elements: MutableList<Pair<String, () -> Boolean>>) {
        while (true) {
            showElements(title, elements)

            print("Введите номер пункта меню: ")
            val input = scanner.nextLine()

            val index = input.toIntOrNull()
            if (index == null){
                println("Ошибка, введите цифру")
                continue
            }
            if(index < 0 || index >= elements.size){
                println("Ошибка, такого пункта меню не существует")
                continue
            }

            val result = elements[index].second()
            if (!result){
                break
            }
        }
    }

    private fun showElements(title: String, elements: MutableList<Pair<String, () -> Boolean>>) {
        println(title)
        elements.forEachIndexed { index, element -> println("$index: ${element.first}") }
    }
}