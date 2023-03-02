package com.lindenlabs.truckrouter

actual class ResourceReader {
    actual operator fun invoke(): String {
        return """
            {
              "shipments": [
                "215 Osinski Manors",
                "9856 Marvin Stravenue",
                "7127 Kathlyn Ferry",
                "987 Champlin Lake",
                "63187 Volkman Garden Suite 447",
                "75855 Dessie Lights",
                "1797 Adolf Island Apt. 744",
                "2431 Lindgren Corners",
                "8725 Aufderhar River Suite 859",
                "79035 Shanna Light Apt. 322"
              ],
              "drivers": [
                "Everardo Welch",
                "Orval Mayert",
                "Howard Emmerich",
                "Izaiah Lowe",
                "Monica Hermann",
                "Ellis Wisozk",
                "Noemie Murphy",
                "Cleve Durgan",
                "Murphy Mosciski",
                "Kaiser Sose"
              ]
            }
        """
    }
}