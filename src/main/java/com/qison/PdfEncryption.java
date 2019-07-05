package com.qison;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

/**
 * User: MD Tausif Ahmad
 * Kindly Follow below link for reference
 * @link https://pdfbox.apache.org/
 * @link https://pdfbox.apache.org/docs/2.0.8/javadocs/
 */
public class PdfEncryption {

    public static void main(String[] args){

        try (final PDDocument doc = new PDDocument()){

            //A page in a PDF document. we can create multiple page in a pdf.
            PDPage page = new PDPage();
            doc.addPage(page);


            /**
             *  AccessPermission represents the access permissions to a document.
             *  below are various permission we can set through setter method
             */
            AccessPermission permission = new AccessPermission();
            permission.setCanPrint(false);
            permission.setCanPrintDegraded(false);
            permission.setCanExtractContent(false);
            permission.setCanExtractForAccessibility(false);
            permission.setCanFillInForm(true);
            permission.setCanModify(false);
            permission.setReadOnly();

            /**
             * The protection policy to add to a document for password-based protection.
             * If you want other policy then Look at PublicKeyProtectionPolicy class
             * https://pdfbox.apache.org/docs/2.0.8/javadocs/org/apache/pdfbox/pdmodel/encryption/PublicKeyProtectionPolicy.html
             */
            StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy("vinay", "password", permission);
            protectionPolicy.setEncryptionKeyLength(128);
            protectionPolicy.setPreferAES(true);
            protectionPolicy.setPermissions(permission);
            doc.protect(protectionPolicy);


            //For page content and styling
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 18);
            content.newLineAtOffset(100, 700);
            content.showText("This is sample Text inside pdf file on page 1");
            content.endText();
            content.close();

            doc.save(new File("passwordProtectedDoc.pdf"));
        } catch (IOException e){
            System.err.println("SomeThing went wrong" + e);
        }
    }
}
