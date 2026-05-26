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
fun TermsOfService(
    modifier: Modifier = Modifier
) {
    val colors = LocalColorScheme.current
    val typography = LocalAppTypography.current
    val verticalScrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.tertiary,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            fontWeight = typography.titleMedium.fontWeight,
            fontSize = typography.titleMedium.fontSize,
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Terms of Service\n\n")
                }

                append("These Terms of Service (\"Terms\") govern your use of the RateMyIdea mobile application (\"Application\"), provided by Patryk Roman Mleczko (\"Service Provider\").\n\n")

                append("By downloading, accessing, or using the Application, you agree to be bound by these Terms. If you do not agree, you must not use the Application.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Eligibility\n\n")
                }

                append("You must be at least 18 years old or have the consent of a legal guardian to use the Application.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("License\n\n")
                }

                append("The Service Provider grants you a limited, non-exclusive, non-transferable, revocable license to use the Application for personal, non-commercial purposes.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Use of the Application\n\n")
                }

                append("You agree to use the Application only in compliance with applicable laws and these Terms.\n\n")

                append("You may not:\n\n" +
                        "- Copy, modify, or distribute the Application\n" +
                        "- Reverse engineer or attempt to extract source code\n" +
                        "- Use the Application for illegal, harmful, or abusive purposes\n" +
                        "- Interfere with the functionality or security of the Application\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("User Content & Moderation\n\n")
                }

                append("Users may submit, post, or share content within the Application (\"User Content\"). You are solely responsible for your User Content.\n\n")

                append("You agree not to post content that is unlawful, offensive, abusive, defamatory, hateful, discriminatory, sexually explicit, misleading, or otherwise inappropriate.\n\n")

                append("The Service Provider reserves the right, at its sole discretion, to review, moderate, remove, or restrict access to any User Content at any time and without prior notice.\n\n")

                append("This includes content submitted through paid features. Payment does not grant any right to keep content published.\n\n")

                append("No refunds will be issued for content that is removed due to violations of these Terms or because it is deemed objectionable.\n\n")

                append("The Service Provider is under no obligation to monitor User Content but reserves the right to do so.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("In-App Purchases\n\n")
                }

                append("The Application may offer optional in-app purchases via Google Play Billing.\n\n")

                append("All payments are processed by Google and are subject to Google’s terms and refund policies.\n\n")

                append("By making a purchase, you acknowledge that digital content may be delivered immediately and that you may lose your statutory right of withdrawal once performance has begun, where applicable.\n\n")

                append("Prices may vary depending on your region and are displayed within the Application.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Advertising\n\n")
                }

                append("The Application may display advertisements provided by third-party services such as Google AdMob.\n\n")

                append("Ads may be personalized depending on your consent and applicable laws.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Third-Party Services\n\n")
                }

                append("The Application may include third-party services. The Service Provider is not responsible for their content or practices.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Availability\n\n")
                }

                append("The Service Provider does not guarantee that the Application will be available at all times or free from interruptions, errors, or security issues.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Disclaimer\n\n")
                }

                append("The Application is provided \"as is\" without warranties of any kind.\n\n")

                append("The Application does not constitute financial, legal, or professional advice.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Limitation of Liability\n\n")
                }

                append("To the maximum extent permitted by law, the Service Provider shall not be liable for indirect or consequential damages.\n\n")

                append("Liability for intent and gross negligence remains unaffected.\n\n")

                append("In cases of slight negligence, liability is limited to foreseeable damages typical for the contract.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Indemnification\n\n")
                }

                append("You agree to indemnify and hold harmless the Service Provider from any claims arising from your use of the Application or violation of these Terms.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Termination\n\n")
                }

                append("The Service Provider may suspend or terminate access to the Application at any time, with or without notice.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Changes\n\n")
                }

                append("These Terms may be updated from time to time. Continued use of the Application constitutes acceptance of the updated Terms.\n\n")

                append("Effective date: 2026-03-01\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Governing Law\n\n")
                }

                append("These Terms are governed by the laws of Germany.\n\n")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colors.primary
                    )
                ) {
                    append("Contact\n\n")
                }

                append("If you have any questions regarding these Terms, please contact:\n\n")

                withStyle(
                    style = SpanStyle(
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