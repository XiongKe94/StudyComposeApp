package com.xk.study.studycomposeapp.ui.page

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.DeleteSweep
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material.icons.twotone.FileCopy
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.xk.common.cascademenu.CascadeMenu
import com.xk.common.cascademenu.CascadeMenuItem
import com.xk.common.cascademenu.cascadeMenu
import com.xk.common.theme.components.Material3Card

/**
 * @author xiongke
 * @Date 2023/09/01
 */
private const val defaultScreen = false

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomePage() {
    val isWiderScreen = rememberSaveable { mutableStateOf(defaultScreen) }
    val isMenuOpen = rememberSaveable { mutableStateOf(true) }
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End,
        ) {
            TopAppBar(title = { Text(text = "HomePage") },
                actions = {
                    IconButton(onClick = { isWiderScreen.value = !isWiderScreen.value }) { Icon(Icons.Filled.Bolt, contentDescription = null) }
                    IconButton(onClick = { isMenuOpen.value = true }) { Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null) }
                })
            Box(contentAlignment = Alignment.TopEnd) {
                HomeScreenContent(isWiderScreen.value)
                Box { pageMenu(isMenuOpen.value, { isMenuOpen.value = false }, { isMenuOpen.value = false }) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() = HomePage()

@Composable
private fun HomeScreenContent(isWiderScreen: Boolean) {
    val homeScreenListItems = mutableListOf<String>()
    for (i in 1..100) {
        homeScreenListItems.add(i.toString())
    }
    val dataList = remember { homeScreenListItems }
    Column(modifier = Modifier.fillMaxSize()) {
        if (isWiderScreen) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(dataList.size) {
                    Material3Card(
                        modifier = Modifier
                            .height(150.dp)
                            .padding(8.dp),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = dataList[it], modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        } else {
            LazyColumn {
                items(dataList) {
                    Button(
                        onClick = { }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = it, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun pageMenu(isOpen: Boolean = false, setIsOpen: (Boolean) -> Unit, itemSelected: (String) -> Unit) {
    val menu = getMenu()
    CascadeMenu(
        isOpen = isOpen,
        menu = menu,
        onItemSelected = itemSelected,
        onDismiss = { setIsOpen(false) },
        offset = DpOffset(8.dp, 0.dp),
    )
}

private fun getMenu(): CascadeMenuItem<String> {
    val menu = cascadeMenu {
        item("about", "About") {
            icon(Icons.TwoTone.Language)
        }
        item("copy", "Copy") {
            icon(Icons.TwoTone.FileCopy)
        }
        item("share", "Share") {
            icon(Icons.TwoTone.Share)
            item("to_clipboard", "To clipboard") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
            item("as_a_file", "As a file") {
                item("pdf", "PDF")
                item("epub", "EPUB")
                item("web_page", "Web page")
                item("microsoft_word", "Microsoft word")
            }
        }
        item("remove", "Remove") {
            icon(Icons.TwoTone.DeleteSweep)
            item("yep", "Yep") {
                icon(Icons.TwoTone.Done)
            }
            item("go_back", "Go back") {
                icon(Icons.TwoTone.Close)
            }
        }
    }
    return menu
}