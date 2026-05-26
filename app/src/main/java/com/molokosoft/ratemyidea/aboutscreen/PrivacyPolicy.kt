package com.molokosoft.ratemyidea.aboutscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.rememberScrollState
import com.molokosoft.ratemyidea.ui.theme.LocalAppTypography
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.molokosoft.ratemyidea.ui.theme.LocalColorScheme

@Composable
fun PrivacyPolicy (
    modifier: Modifier = Modifier
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val verticalScrollState = rememberScrollState()

    Box (
        modifier = modifier
            .fillMaxWidth()
            .background (
                color = colors.tertiary,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text (
            fontWeight = typography.titleMedium.fontWeight,
            fontSize = typography.titleMedium.fontSize,
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Privacy Policy\n\n")
                }

                append("This Privacy Policy applies to the RateMyIdea app (\"Application\") for mobile devices, created by Patryk Roman Mleczko (\"Service Provider\"). This Application is provided as an ad-supported service.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Information Collection\n\n")
                }

                append("The Application itself does not collect or transmit personal data to servers operated by the Service Provider.\n\n")

                append("All user data (such as financial entries or preferences) is stored locally on your device.\n\n")

                append("However, the Application uses third-party services that may collect certain information automatically, including:\n\n" +
                        "- IP address\n" +
                        "- App usage information (pages visited, time spent, interactions)\n" +
                        "- Device information (operating system, device identifiers such as advertising ID)\n\n")

                append("The Application does not collect precise location data.\n\n")

                append("Camera access is used only for features within the Application that require capturing images. The Application does not access or collect your photos without your explicit interaction.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Third-Party Services\n\n")
                }

                append("The Application integrates third-party services that may collect and process data independently. These include:\n\n" +
                        "- Google AdMob (advertising)\n" +
                        "- Google Play Billing (in-app purchases)\n\n")

                append("These services may collect data such as device identifiers, IP address, and app usage information.\n\n")

                append("This data is used for:\n\n" +
                        "- Advertising and marketing\n" +
                        "- Analytics and app improvement\n" +
                        "- App functionality\n\n")

                append("Third-party services operate under their own privacy policies and may process data independently.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Advertising\n\n")
                }

                append("The Application displays advertisements via Google AdMob.\n\n")

                append("Ads may be personalized or non-personalized depending on your location and consent choices. Users in certain regions (such as the European Economic Area) may be asked to provide consent before personalized ads are shown.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("In-App Purchases\n\n")
                }

                append("The Application offers optional in-app purchases processed through Google Play Billing.\n\n")

                append("The Service Provider does not collect or store any payment information such as credit card details. All payment data is handled securely by Google.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Data Retention\n\n")
                }

                append("Since the Service Provider does not collect personal data on its own servers, no user data is retained externally.\n\n")

                append("Any data processed by third-party services is subject to their respective data retention policies.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Children\n\n")
                }

                append("The Application is not intended for children under the age of 13. The Service Provider does not knowingly collect personal data from children.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Security\n\n")
                }

                append("We take reasonable measures to protect data stored locally on your device.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Changes\n\n")
                }

                append("This Privacy Policy may be updated from time to time. Continued use of the Application constitutes acceptance of any changes.\n\n")

                append("This Privacy Policy is effective as of 2026-04-09.\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Contact Us\n\n")
                }

                append("If you have any questions regarding this Privacy Policy, please contact:\n\n")

                withStyle (
                    style = SpanStyle (
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("mleczko.patryk.roman@gmail.com\n\n")
                }

                append("© RateMyIdea. All rights reserved.")
            },
            color = colors.primary,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .verticalScroll(verticalScrollState)
        )
    }
}