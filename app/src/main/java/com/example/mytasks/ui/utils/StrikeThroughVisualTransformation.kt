package com.example.mytasks.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration

class StrikeThroughVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val builder = AnnotatedString.Builder()
        val rawText = text.text
        
        var i = 0
        val mapping = mutableListOf<Int>()
        var transformedIndex = 0
        
        while (i < rawText.length) {
            if (i + 1 < rawText.length && rawText[i] == '~' && rawText[i+1] == '~') {
                val end = rawText.indexOf("~~", i + 2)
                if (end != -1) {
                    // Start strike-through
                    i += 2
                    val startTransformed = transformedIndex
                    while (i < end) {
                        builder.append(rawText[i])
                        mapping.add(i) // map transformed index to original index
                        i++
                        transformedIndex++
                    }
                    builder.addStyle(
                        style = SpanStyle(textDecoration = TextDecoration.LineThrough),
                        start = startTransformed,
                        end = transformedIndex
                    )
                    i += 2 // Skip closing ~~
                    continue
                }
            }
            builder.append(rawText[i])
            mapping.add(i)
            i++
            transformedIndex++
        }
        
        // Add one more mapping for the cursor at the end
        mapping.add(rawText.length)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Find the first index in mapping that is >= offset
                val found = mapping.indexOfFirst { it >= offset }
                return if (found != -1) found else mapping.size - 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset in mapping.indices) mapping[offset] else rawText.length
            }
        }

        return TransformedText(builder.toAnnotatedString(), offsetMapping)
    }
}
