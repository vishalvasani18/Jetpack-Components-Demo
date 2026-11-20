package com.example.myapplication.composable

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.util.LanguageManager

@Composable
fun LanguageScreen(
    onLanguageSaved: () -> Unit
) {

    val context = LocalContext.current

    val currentLanguage = LanguageManager.getCurrentLanguage(context)

    var selectedLanguage by remember {
        mutableStateOf(currentLanguage)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        LanguageItem(
            language = stringResource(R.string.english),
            isSelected = selectedLanguage == LanguageManager.ENGLISH,
            onClick = {
                selectedLanguage = LanguageManager.ENGLISH
            }
        )

        HorizontalDivider()

        LanguageItem(
            language = stringResource(R.string.hindi),
            isSelected = selectedLanguage == LanguageManager.HINDI,
            onClick = {
                selectedLanguage = LanguageManager.HINDI
            }
        )

        HorizontalDivider()

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {
                LanguageManager.changeLanguage(context,selectedLanguage)
                onLanguageSaved()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.save)
            )
        }
    }
}

@Composable
private fun LanguageItem(
    language: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected"
            )
        }
    }
}
