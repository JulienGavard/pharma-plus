package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IdentifiantPrdTest {

    @Test
    fun `objectif et criteres de succes sont couvrables`() {
        assertTrue(IdentifiantPrd("OBJ").estCouvrable)
        assertTrue(IdentifiantPrd("CS-1").estCouvrable)
        assertTrue(IdentifiantPrd("CL-1").estCouvrable)
    }

    @Test
    fun `probleme et utilisateur ne sont pas couvrables`() {
        assertFalse(IdentifiantPrd("PROB").estCouvrable)
        assertFalse(IdentifiantPrd("USR").estCouvrable)
    }

    @Test
    fun `un identifiant exempte n'est pas couvrable`() {
        assertFalse(IdentifiantPrd("CL-4", exempteeDeCouverture = true).estCouvrable)
    }

    @Test
    fun `le prefixe est la partie avant le tiret`() {
        assertEquals("CS", IdentifiantPrd("CS-12").prefixe)
        assertEquals("OBJ", IdentifiantPrd("OBJ").prefixe)
    }
}
