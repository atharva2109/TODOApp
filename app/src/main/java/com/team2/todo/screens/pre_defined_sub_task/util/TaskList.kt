package com.team2.todo.screens.pre_defined_sub_task.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.team2.todo.R
import com.team2.todo.data.entities.SubTodo
import java.time.LocalDateTime

/**
 * Created by Manu KJ on 12/4/23.
 */

object TaskList {


    private fun createSubTodo(
        todoId: Long,
        title: String?,
        description: String?,
        image: Bitmap?,
        priority: Int?,
        status: Boolean?,
        dueDate: LocalDateTime?
    ): SubTodo {
        return SubTodo(
            subTodoId = 0,
            todoId = todoId,
            title = title,
            description = description,
            image = image,
            createdDate = LocalDateTime.now(),
            dueDate = dueDate,
            status = status,
            priority = priority
        )
    }

    fun getSubTodoList(todoId: Long): List<SubTodo> {
        val todoList = mutableListOf<SubTodo>()

        todoList.add(
            createSubTodo(
                todoId,
                "Marketing Strategy",
                "Develop a comprehensive marketing plan utilizing online listings, social media, and other channels for effective property promotion.",
                null,
                3,
                null,
                null
            )
        )

        todoList.add(
            createSubTodo(
                todoId,
                "Showings and Open Houses",
                "Coordinate and host multiple showings, including open house events to engage potential buyers and showcase property features.",
                null,
                4,
                null,
                null
            )
        )


        todoList.add(
            createSubTodo(
                todoId,
                "Negotiation",
                "Skillfully present and negotiate offers between the seller and potential buyers to reach a mutually beneficial agreement.",
                null,
                6,
                null,
                null
            )
        )

        todoList.add(
            createSubTodo(
                todoId,
                "Paperwork and Documentation",
                "Handle the necessary paperwork, contracts, disclosures, and legal documents involved in the property sale transaction.",
                null,
                7,
                null,
                null
            )
        )

        todoList.add(
            createSubTodo(
                todoId,
                "Inspections and Appraisals",
                "Coordinate inspections and appraisals essential for satisfying buyer requirements and ensuring property value.",
                null,
                8,
                null,
                null
            )
        )

        todoList.add(
            createSubTodo(
                todoId,
                "Closing Preparations",
                "Facilitate the closing process, ensuring all parties fulfill their obligations and complete required paperwork accurately.",
                null,
                9,
                null,
                null
            )
        )

        todoList.add(
            createSubTodo(
                todoId,
                "Post-Sale Follow-Up",
                "Provide necessary support post-sale to ensure a smooth transition for the buyer and seller, addressing any potential issues.",
                null,
                10,
                null,
                null
            )
        )

        return todoList
    }

}