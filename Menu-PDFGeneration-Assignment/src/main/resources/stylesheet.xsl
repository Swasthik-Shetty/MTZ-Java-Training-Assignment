<?xml version="1.0" encoding="UTF-8"?>
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format"
         xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Layout definition -->
    <fo:layout-master-set>
        <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin="2cm">
            <fo:region-body margin="1cm"/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <!-- Page sequence -->
    <fo:page-sequence master-reference="A4">
        <fo:flow flow-name="xsl-region-body">

            <!-- Title block -->
            <fo:block font-size="24pt" font-weight="bold" text-align="center" space-after="20pt">
                Invoice
            </fo:block>

            <!-- Customer name -->
            <fo:block font-size="14pt" font-weight="bold" space-after="10pt">
                Customer: <xsl:value-of select="/OrderInfoDTO/customerName"/>
            </fo:block>

            <!-- Table for order details -->
            <fo:table table-layout="fixed" width="100%" border="0.5pt solid black">
                <fo:table-column column-width="10%"/>
                <fo:table-column column-width="40%"/>
                <fo:table-column column-width="20%"/>
                <fo:table-column column-width="15%"/>
                <fo:table-column column-width="15%"/>

                <fo:table-header>
                    <fo:table-row background-color="#CCCCCC">
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold">ID</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold">Name</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold">Quantity</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold">Price</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="5pt" border="0.5pt solid black">
                            <fo:block font-weight="bold">Total Price</fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-header>

                <fo:table-body>
                    <xsl:for-each select="/OrderInfoDTO/orders/order">
                        <fo:table-row>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block><xsl:value-of select="id"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block><xsl:value-of select="itemName"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block><xsl:value-of select="quantity"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block>$<xsl:value-of select="itemPrice"/></fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="5pt" border="0.5pt solid black">
                                <fo:block>$<xsl:value-of select="totalPrice"/></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>

            <!-- Total amount -->
            <fo:block font-size="14pt" font-weight="bold" space-before="20pt">
                Total Amount: $<xsl:value-of select="/OrderInfoDTO/completeTotal"/>
            </fo:block>

        </fo:flow>
    </fo:page-sequence>
</fo:root>
