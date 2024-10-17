package br.laemcasa.`class`

import android.os.Build
import androidx.annotation.RequiresApi
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.sql.Timestamp
import java.time.Instant
import java.util.UUID

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

class Task {
    var id: UUID = UUID.randomUUID()

    var name: String? = null

    var descripition: String ? = null

    @RequiresApi(Build.VERSION_CODES.O)
    var createdAt: Timestamp = Timestamp.from(Instant.now()) as Timestamp

    @RequiresApi(Build.VERSION_CODES.O)
    var updateAt: Timestamp = Timestamp.from(Instant.now()) as Timestamp

    @RequiresApi(Build.VERSION_CODES.O)
    var finishedAt: Timestamp = Timestamp.from(Instant.now()) as Timestamp

}