<?xml version="1.0" encoding="UTF-8"?>
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format"
         xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Layout definition -->
    <fo:layout-master-set>
        <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin="2cm">
            <fo:region-body margin-top="3cm" margin-bottom="1cm"/>
            <fo:region-before extent="2cm"/>  <!-- Space allocated for header -->
            <fo:region-after extent="1cm"/>    <!-- Space allocated for footer (if needed) -->
        </fo:simple-page-master>
    </fo:layout-master-set>

    <!-- Page sequence -->
    <fo:page-sequence master-reference="A4">
        <!-- Static content for the header -->
        <fo:static-content flow-name="xsl-region-before">
            <fo:block font-size="24pt" font-weight="bold" text-align="center" space-after="20pt" font-family="Cursive" font-style ="italic" color="green">
                Hotel Woodlands
            </fo:block>
            <fo:block font-size="18pt" font-weight="bold" text-align="center" space-after="20pt" font-family="Cursive" font-style ="italic" color="green">
                Order Details
            </fo:block>
        </fo:static-content>

        <!-- Static content for the footer with page number -->
        <fo:static-content flow-name="xsl-region-after">
            <fo:block font-size="10pt" text-align="center" space-before="20pt">
                Page <fo:page-number/> of <fo:page-number-citation ref-id="end-of-document"/>
            </fo:block>
        </fo:static-content>

        <!-- Main content of the page -->
        <fo:flow flow-name="xsl-region-body">
            <!-- Customer name -->
            <fo:block font-size="14pt" font-weight="bold" space-after="10pt" font-family="Cursive" font-style ="italic" color="green">
                Customer Name : <fo:inline color = "#3CB371"><xsl:value-of select="/OrderInfoDTO/customerName"/></fo:inline>
            </fo:block>

            <!-- Table for order details -->
            <fo:table table-layout="fixed" width="100%" border="0.5pt solid black">
                <fo:table-column column-width="10%"/>
                <fo:table-column column-width="40%"/>
                <fo:table-column column-width="20%"/>
                <fo:table-column column-width="15%"/>
                <fo:table-column column-width="15%"/>

                <fo:table-header>
                    <fo:table-row background-color="#00FF7F">
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold" font-family="Cursive" font-style ="italic" color="green">Sl No.</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold" font-family="Cursive" font-style ="italic" color="green">Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold" font-family="Cursive" font-style ="italic" color="green">Quantity</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold" font-family="Cursive" font-style ="italic" color="green">Price</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold" font-family="Cursive" font-style ="italic" color="green">Total Price</fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-header>

                <fo:table-body>
                    <xsl:for-each select="/OrderInfoDTO/orders/order">
                        <fo:table-row>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block font-family="Cursive" font-style ="italic" color="green"><xsl:value-of select="position()"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block font-family="Cursive" font-style ="italic" color="green"><xsl:value-of select="itemName"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block font-family="Cursive" font-style ="italic" color="green"><xsl:value-of select="quantity"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block font-family="Cursive" font-style ="italic" color="green">$<xsl:value-of select="itemPrice"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block font-family="Cursive" font-style ="italic" color="green">$<xsl:value-of select="totalPrice"/></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>

            <!-- Total amount -->
            <fo:block font-size="14pt" font-weight="bold" space-before="20pt" font-family="Cursive" font-style ="italic" color="blue">
                Total Amount: $<fo:inline><xsl:value-of select="/OrderInfoDTO/completeTotal"/></fo:inline>
            </fo:block>

            <!-- Total amount after GST -->
            <fo:block font-size="14pt" font-weight="bold" space-before="20pt" font-family="Cursive" font-style ="italic" color="red">
                Total Amount After Applying 5% GST: $<fo:inline><xsl:value-of select="/OrderInfoDTO/totalWithGST"/></fo:inline>
            </fo:block>

            <!-- Block marking the end of the document -->
            <fo:block id="end-of-document"/>
        </fo:flow>
    </fo:page-sequence>
</fo:root>
