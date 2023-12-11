package com.example.todopr.ui.presentance.component.mainScreen


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteDialog(
    title:String,
    body:String,
    onDialogDismiss:()->Unit,
    onConfirmButtonClicked :()->Unit
) {

    AlertDialog(
        onDismissRequest = { onDialogDismiss() },
        confirmButton = {
                        TextButton(onClick = onConfirmButtonClicked) {
                            Text(text = "Yes",
                                style = MaterialTheme.typography.labelMedium)
                        }
        },
        dismissButton = {
            TextButton(onClick = onDialogDismiss) {
                Text(text = "No",
                    style = MaterialTheme.typography.labelMedium)
            }
        },
        title = {
            Text(text = title,
                style = MaterialTheme.typography.labelMedium)
        },

        shape = MaterialTheme.shapes.medium,
        tonalElevation = 4.dp,
        text = {
            Text(text = body,
                style = MaterialTheme.typography.labelMedium)
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewC() {
    NoteDialog(title = "Delete", body = "Are You deleted", onDialogDismiss = { /*TODO*/ }) {

    }
}