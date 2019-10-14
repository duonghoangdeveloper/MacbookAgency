<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="https://www.macstores.vn"
                xmlns="https://www.macstores.vn"
                version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="t:products" xmlns="https://www.macstores.vn">       
        <xsl:element name="products">
            <xsl:variable name="doc" select="document(@link)"/>
            <xsl:variable name="products" select="$doc//div[@class='mkdf-woo-product-list-item-holder']"/>                                   
            <xsl:for-each select="$products">
                <xsl:element name="product">
                    <title>
                        <xsl:value-of select=".//h4[@class='mkdf-product-list-product-title']"/>
                    </title>
                    <price>
                        <xsl:value-of select="translate(normalize-space((.//span[@class='woocommerce-Price-amount amount'])[last()]), '.', '')"/>
                    </price>
                    <image>
                        <xsl:value-of select=".//img/@src"/>
                    </image>
                    <url>
                        <xsl:value-of select=".//a[@class='mkdf-woo-product-info-holder']/@href"/>
                    </url>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
