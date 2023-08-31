package com.basic.template.myapplication.userlist.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.basic.template.myapplication.model.User
import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import com.basic.template.myapplication.util.TestUITag

@Composable
fun UserListScreen(userListViewModel: UserListViewModel) {
    LaunchedEffect(Unit) { userListViewModel.fetchUserListApiViaViewModel() }
    val uiState by userListViewModel.uiState.collectAsState()
    if (uiState is NetworkResult.Success) {
        val userListRoot = (uiState.data as UserListRoot)
        if (userListRoot.userModelList != null && userListRoot.userModelList?.size!! > 0) {
            UserListItem(
                userList = userListRoot.userModelList!!
            )
        }
    }
}

@Composable
fun UserListItem(userList: List<User>) {
    Column {
        Text(
            text = "User List",
            modifier = Modifier
                .fillMaxWidth()
                .testTag(TestUITag.USER_LIST_TITLE)
        )
        LazyColumn {
            items(userList) { user ->
                UserMessageRow(user, onClick = {})
            }
        }
    }
}

@Composable
fun UserMessageRow(user: User, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.userAvatar,
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = user.userFirstName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.userLastName,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.userEmail.toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen()
}