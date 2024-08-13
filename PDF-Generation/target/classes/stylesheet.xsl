<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin-top="0cm" margin-bottom="0cm" margin-left="2cm" margin-right="2cm">
                    <fo:region-body margin-top="2cm" margin-bottom="2cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">

                    <!-- Header Section -->
                    <fo:block-container width="100%" padding="10pt" background-color="black" color="white">
                        <fo:block font-family="Arial" font-size="24pt" font-weight="bold" text-align="center">INVOICE</fo:block>
                    </fo:block-container>

                    <fo:block font-family="Arial" font-size="12pt" space-before="20pt" space-after="10pt">
                        <fo:inline>Invoice Number: INV-01234</fo:inline>
                        <fo:inline space-before="8cm">Date: April 11, 2023</fo:inline>
                    </fo:block>

                    <fo:block font-family="Arial" font-size="12pt" space-after="20pt">
                        <fo:inline>123 Anywhere St., Any City</fo:inline>
                        <fo:inline space-before="8cm">+123-456-7890</fo:inline>
                    </fo:block>

                    <!-- Bill To Section -->
                    <fo:block font-family="Arial" font-size="12pt" font-weight="bold" space-after="10pt">BILL TO:</fo:block>
                    <fo:block font-family="Arial" font-size="12pt" space-after="20pt">
                        Kimberly Nguyen<fo:block/>
                        123 Anywhere St.<fo:block/>
                        Any City, ST 12345
                    </fo:block>

                    <!-- Itemized List Table -->
                    <fo:table table-layout="fixed" width="100%" space-after="20pt" border-collapse="separate" border-spacing="0.5pt">
                        <fo:table-column column-width="10%"/>
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-header>
                            <fo:table-row background-color="black" color="white">
                                <fo:table-cell padding="5pt">
                                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" text-align="center">ITEM</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt">
                                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" text-align="center">DESCRIPTION</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt">
                                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" text-align="center">PRICE</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt">
                                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" text-align="center">AMOUNT</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">1.</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt">Consulting Services</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$100</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$500</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">2.</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt">Website Development</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$5,000</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$5,000</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">3.</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt">Graphic Design Services</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$50</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="5pt" border="0.5pt solid black">
                                    <fo:block font-family="Arial" font-size="10pt" text-align="center">$250</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <!-- Subtotal, Sales Tax, and Total -->
                    <fo:block-container width="100%" space-before="20pt" padding-top="10pt" border-top="0.5pt solid black">
                        <fo:block font-family="Arial" font-size="12pt" text-align="right" space-after="10pt">
                            Sub Total: <fo:inline font-weight="bold">$6,250</fo:inline>
                        </fo:block>
                        <fo:block font-family="Arial" font-size="12pt" text-align="right" space-after="10pt">
                            Sales Tax: <fo:inline font-weight="bold">$625</fo:inline>
                        </fo:block>
                        <fo:block font-family="Arial" font-size="14pt" font-weight="bold" text-align="right" space-after="20pt">
                            TOTAL: $6,875
                        </fo:block>
                    </fo:block-container>

                    <!-- Payment Information -->
                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" space-after="10pt">PAYMENT INFORMATION:</fo:block>
                    <fo:block font-family="Arial" font-size="10pt" space-after="10pt">
                        Bank: Borecille Bank<fo:block/>
                        Name: Margarita Perez<fo:block/>
                        Account: 0123 4567 8901
                    </fo:block>

                    <!-- Terms and Conditions -->
                    <fo:block font-family="Arial" font-size="10pt" font-weight="bold" space-after="10pt">
                        TERM AND CONDITIONS:
                    </fo:block>
                    <fo:block font-family="Arial" font-size="10pt">
                        Payment is due 30 days from the invoice date.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
