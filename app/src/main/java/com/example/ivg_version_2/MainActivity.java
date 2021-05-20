package com.example.ivg_version_2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;



public class MainActivity extends AppCompatActivity {
    Button btn_create_pdf, Issuer_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create_pdf = (Button)findViewById(R.id.btn_create_pdf);
        Issuer_info = (Button)findViewById(R.id.Issuer_info);





    }



    private void createPDFFile(String path) {
        if(new File(path).exists())
            new File(path).delete();
        try {
            Document document = new Document();
            //save
            PdfWriter.getInstance(document, new FileOutputStream(path));
            //open to write
            document.open();

            //Setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("MJeste");
            document.addCreator("Manasi Jeste");

            //Font Settting
            BaseColor colorAccent = new BaseColor(29,70,105,255);
            BaseColor Reci_color = new BaseColor(54,91,191,255);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //custom font
            BaseFont FontName = BaseFont.createFont("assets/fonts/Brandon_Medium.otf","UTF-8", BaseFont.EMBEDDED);
            Font titlefont = new Font(FontName,30.0f,Font.NORMAL,colorAccent);
            addNewItem(document,"Invoice Generator", Element.ALIGN_CENTER,titlefont);

            //Add more
            Font OrderName = new Font(FontName,fontSize,Font.NORMAL,colorAccent);
            Font OrderValueName = new Font(FontName,fontSize,Font.NORMAL,Reci_color);
            addNewItem(document,global.Recipient,Element.ALIGN_LEFT,OrderName);
            addNewItem(document,global.phno_R,Element.ALIGN_LEFT,OrderName);
            addNewItem(document,global.email_R,Element.ALIGN_LEFT,OrderName);
            addNewItem(document,global.addr_R,Element.ALIGN_LEFT,OrderName);

            addNewItem(document,global.Issuer,Element.ALIGN_RIGHT,OrderValueName);
            addNewItem(document,global.phno_I,Element.ALIGN_RIGHT,OrderValueName);
            addNewItem(document,global.email_I,Element.ALIGN_RIGHT,OrderValueName);
            addNewItem(document,global.addr_I,Element.ALIGN_RIGHT,OrderValueName);
            addLineSeperator(document);
            addLineSpace(document);
            addNewItem(document,"Product Details",Element.ALIGN_CENTER,titlefont);

            //Item
            addNewItemWithLeftandRight(document,"pizza 25","(0.0%)",titlefont,OrderName);
            addNewItemWithLeftandRight(document,"12.0*1000","12000.0",titlefont,OrderName);

            addLineSeperator(document);

            document.close();
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();

            printPDF();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPDF() {
        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printDocumentAdapter = new pdfDocumentAdapter(MainActivity.this,common.getAppPath(MainActivity.this)+"test_pdf.pdf");
            printManager.print("Document",printDocumentAdapter,new PrintAttributes.Builder().build());

        }catch (Exception ex){
            Log.e("PDF",""+ ex.getMessage());
        }
    }

    private void addNewItemWithLeftandRight(Document document, String textleft, String textright, Font textleftfont, Font textrightfont) throws DocumentException {
        Chunk Chunkleft = new Chunk(textleft,textleftfont);
        Chunk Chunkright = new Chunk(textright,textrightfont);

        Paragraph p = new Paragraph(Chunkleft);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(Chunkright);
        document.add(p);

    }


    private void addLineSeperator(Document document) throws DocumentException {

        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(""));

    }

    private void addNewItem(Document document, String text, int align, Font font) throws DocumentException {
        Chunk chunk = new Chunk(text,font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }


    public void onIFClick(View view) {
        Intent intent = new Intent(this, Issuer.class);
        startActivity(intent);

    }
    public void onCreatepdfClick(View view) {
        savingpdf();

    }

    private void savingpdf (){
        Dexter.withActivity(this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {


                        btn_create_pdf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                createPDFFile(common.getAppPath(MainActivity.this)+"test_pdf.pdf");
                            }
                        });






                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

    }


    public void onRIClick(View view) {

        Intent intent = new Intent(this, Recipient.class);
        startActivity(intent);


    }

    public void onELClick(View view) {
        Intent intent = new Intent(this, Expense.class);
        startActivity(intent);

    }

    public void onUploadClick(View view) {

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);



    }
}
