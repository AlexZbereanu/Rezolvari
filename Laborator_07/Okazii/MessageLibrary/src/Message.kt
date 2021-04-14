import java.text.SimpleDateFormat
import java.util.*

class Message private constructor( val user: String, val sender: String, val body: String,  val timestamp: Date) {
    companion object {
        fun create(user: String, sender: String, body: String): Message {
            return Message(user, sender, body, Date())
        }

        fun deserialize(msg: ByteArray): Message {
            val msgString = String(msg)
            val (timestamp,user, sender, body) = msgString.split(' ', limit = 4)

            return Message(user, sender, body, Date(timestamp.toLong()))
        }
    }

    fun serialize(): ByteArray {
        return "${timestamp.time} $user $sender $body\n".toByteArray()
    }

    override fun toString(): String {
        val dateString = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp)
        return "[$dateString] $user >>> $body"
    }
}

fun main(args: Array<String>) {
    val msg = Message.create("Alex","localhost:4848", "test mesaj")
    println(msg)
    val serialized = msg.serialize()
    val deserialized = Message.deserialize(serialized)
    println(deserialized)
}