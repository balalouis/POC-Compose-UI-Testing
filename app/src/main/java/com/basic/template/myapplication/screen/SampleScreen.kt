package com.basic.template.myapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.basic.template.myapplication.R

@Composable
fun SampleScreen() {
    val userName = remember {
        mutableStateOf(TextFieldValue("eve.holt@reqres.in"))
    }
    val password = remember {
        mutableStateOf(TextFieldValue("cityslicka"))
    }
    val age = remember {
        mutableStateOf(TextFieldValue("12"))
    }
    Column {
        LoginTextFields(userName = userName, password = password, age = age)
        Button(onClick = {

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Registration")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextFields(
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    age: MutableState<TextFieldValue>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = { Text(text = stringResource(id = R.string.email)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth(),
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
        )

        TextField(
            value = age.value,
            onValueChange = { age.value = it },
            label = { Text(text = stringResource(id = R.string.age)) },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
        )
        Gender()
    }
}

@Composable
fun Gender() {
    val gender = listOf("Male", "Female")
    val selectedItem = remember {
        gender[0]
    }

    Row {
        gender.forEach {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    modifier = Modifier.padding(end = 16.dp),
                    selected = selectedItem == it,
                    onClick = { /*TODO*/ })
                Text(it)
            }
        }
    }
}