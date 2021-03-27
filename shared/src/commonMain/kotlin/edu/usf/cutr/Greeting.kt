package edu.usf.cutr

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}