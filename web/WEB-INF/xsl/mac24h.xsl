<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="https://mac24h.vn"
                xmlns="https://mac24h.vn"
                version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="t:products" xmlns="https://mac24h.vn">       
        <xsl:element name="products">
            <xsl:variable name="doc" select="document(@link)"/>
            <xsl:variable name="products" select="$doc//div[@class='grid-list']//form"/>                                   
            <xsl:for-each select="$products">
                <xsl:element name="product">
                    <title>
                        <xsl:value-of select=".//a[@class='product-title']"/>
                    </title>
                    <price>
                        <xsl:value-of select="translate(normalize-space(.//span[@id and @class='ty-price-num']), '.', '')"/>
                    </price>
                    <image>
                        <xsl:value-of select=".//img[@id]/@src"/>
                    </image>
                    <url>
                        <xsl:value-of select=".//a[@class='product-title']/@href"/>
                    </url>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
