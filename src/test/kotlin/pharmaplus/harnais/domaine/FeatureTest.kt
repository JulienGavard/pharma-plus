package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FeatureTest {

    @Test
    fun `les numeros sont extraits de l'id`() {
        assertEquals(1 to 2, feature(id = "feat-1.2").numeros)
    }

    @Test
    fun `les numeros sont null si l'id est malforme`() {
        assertNull(feature(id = "feat-1").numeros)
        assertNull(feature(id = "feat-a.b").numeros)
        assertNull(feature(id = null).numeros)
    }
}
