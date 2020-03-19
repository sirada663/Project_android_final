package com.example.myappproject

class Student(var name: String, var score: Float) {

    companion object {
        fun getSampleStudentData(size: Int): ArrayList<Student> {
            val student: ArrayList<Student> = ArrayList()
            for (i in 0 until size) {
                student.add(Student("coffee & berkary $i", Math.random().toFloat() * 100))
            }
            return student
        }
    }

}
