package com.qison;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * User: Md. Tausif Ahmad
 */
public class PdfDecryption {

    public static void main(String[] args) throws Exception{

        try (PDDocument document = PDDocument.load(new File("passwordProtectedDoc.pdf"), "password")) {

            document.setAllSecurityToBeRemoved(true);

            document.save(new File("passwordProtectedDoc.pdf"));


        } catch (IOException e){
            System.err.println("Exception while trying to read pdf document - " + e);
        }
    }
}
