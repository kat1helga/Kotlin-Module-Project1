import java.util.Scanner

object MainMenu {
    private val scanner = Scanner(System.`in`)
    fun show(archivesList: MutableList<Pair<String, Archive>>) {
        val title = "Список архивов:  "
        val elements = mutableListOf<Pair<String, () -> Boolean>>()

        elements.add(Pair("Создать архив") {
            val archivePair = showCreateArchiveMenu()

            val archiveName = archivePair.first
            val archive = archivePair.second

            archivesList.add(Pair(archiveName, archive))
            elements.add(elements.lastIndex, Pair(archiveName) {
                ArchiveMenu.show(archiveName, archive)
                true
            })

            true
        })
        archivesList.forEach{archivePair ->
            run {
                val archiveName = archivePair.first
                val archive = archivePair.second

                elements.add(Pair(archiveName) {
                    ArchiveMenu.show(archiveName, archive)
                    true
                })
            }
        }
        elements.add(Pair("Выход") {
            false
        })

        Menu.show(title, elements)
    }

    private fun showCreateArchiveMenu() : Pair<String, Archive> {
        val archiveName: String

        while (true) {
            print("Введите имя архива: ")
            val input = scanner.nextLine()

            if (input.isNullOrEmpty()) {
                println("Имя архива не должно быть пустым")
                continue
            }

            archiveName = input
            break
        }

        val archive = Archive(mutableListOf())
        return Pair(archiveName, archive)
    }
}