import java.util.Scanner

object ArchiveMenu {
    private val scanner = Scanner(System.`in`)

    fun show(archiveName: String, archive: Archive) {
        val title = "Архив \"$archiveName\". Список заметок: "
        val elements = mutableListOf<Pair<String, () -> Boolean>>()

        elements.add(Pair("Создать заметку") {
            val notePair = showCreateNoteMenu()

            val noteName = notePair.first
            val note = notePair.second

            archive.notes.add(Pair(noteName, note))
            elements.add(elements.lastIndex, Pair(noteName) {
                NoteMenu.show(noteName, note)
                true
            })

            true
        })
        archive.notes.forEach{notePair ->
            run {
                val noteName = notePair.first
                val note = notePair.second

                elements.add(Pair(noteName) {
                    NoteMenu.show(noteName, note)
                    true
                })
            }
        }
        elements.add(Pair("Выход") {
            false
        })

        Menu.show(title, elements)
    }

    private fun showCreateNoteMenu() : Pair<String, Note> {
        val noteName: String
        val noteText: String

        while (true) {
            print("Введите имя заметки: ")
            val input = scanner.nextLine()

            if (input.isNullOrEmpty()) {
                println("Имя заметки не должно быть пустым")
                continue
            }

            noteName = input
            break
        }

        while (true) {
            print("Введите текст заметки: ")
            val input = scanner.nextLine()

            if (input.isNullOrEmpty()) {
                println("Текст заметки не должен быть пустым")
                continue
            }

            noteText = input
            break
        }

        val note = Note(noteText)
        return Pair(noteName, note)
    }
}