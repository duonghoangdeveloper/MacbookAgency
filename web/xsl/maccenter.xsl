<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:t="http://www.maccenter.vn"
                xmlns="http://www.maccenter.vn"
                version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="t:products" xmlns="http://www.maccenter.vn">       
        <xsl:element name="products">
            <xsl:variable name="doc" select="document(@link)"/>
            <xsl:variable name="products" select="$doc//table[@class='Table-Thin']//td[@class='Td-Thin']"/>                                   
            <xsl:for-each select="$products">
                <xsl:if test=".//a[@class='TitleProduct'][2]">
                    <xsl:element name="product">
                        <title><xsl:value-of select=".//a[@class='TitleProduct'][2]"/></title>
                        <price><xsl:value-of select="translate(normalize-space(substring-before(substring-after(.,'Giá:'),' vn')), '.', '')"/></price>
                        <image>http://www.maccenter.vn/<xsl:value-of select=".//img/@src"/></image>
                        <url>http://www.maccenter.vn/<xsl:value-of select=".//a[@class='TitleProduct']/@href"/></url>
                    </xsl:element>
                </xsl:if>
                
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
