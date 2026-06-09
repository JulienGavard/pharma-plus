package pharmaplus.harnais.domaine

/** Agrégat de tout ce que le harnais valide à un instant donné. */
data class Specifications(
    val prd: Prd?,
    val table: TableDeDerivation?,
    val epics: List<Epic>,
    val features: List<Feature>,
)
