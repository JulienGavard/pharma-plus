package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class EpicTest {

    @Test
    fun `le numero est extrait de l'id`() {
        assertEquals(3, epic(id = "epic-3").numero)
    }

    @Test
    fun `le numero est null si l'id est absent ou malforme`() {
        assertNull(epic(id = "epic-X").numero)
        assertNull(epic(id = null).numero)
    }
}
