object NoteMenu {
    fun show(noteName: String, note: Note) {
        Menu.show(
            title = "Заметка \"$noteName\": ",
            elements = mutableListOf(
                Pair("Прочитать") {
                    println(note.text)
                    true
                },
                Pair("Выход") {
                    false
                }
            )
        )
    }
}