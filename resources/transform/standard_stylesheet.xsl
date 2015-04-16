<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="2.0" xmlns="http://www.w3.org/1999/xhtml">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="Standard">
        <html>
            <head>
                <style type="text/css">
                    body {
                    font-size: 11px;
                    line-height: 15px;
                    font-family: Verdana, Georgia, sans-serif;
                    margin: 10px 25px;
                    }
                    h1{
                    font-size: 16px;
                    line-height: 15px;
                    padding: 0px 0px 0px 0px;
                    font-family: Verdana, Georgia, sans-serif;
                    }
                    table.header {
                    border-style: hidden;
                    width: 100%;
                    }

                    table.subHeader {
                    border-style: hidden;
                    padding: 0px 0px 0px 0px;
                    margin-top: -20px;
                    }

                    table.nav {
                    border: 1px solid black;
                    background-color: #9999CC;
                    font-size: 11px;
                    width: 100%;
                    }

                    table.footer {
                    border-style: hidden;
                    width: 100%;
                    text-align: right;
                    }

                    table.code {
                    border: 1px dashed black;
                    background-color: #CCCCFF;
                    padding: 15px;
                    margin: -20px 0;
                    }

                    td.code {
                    border: 1px dashed black;
                    background-color: #CCCCFF;
                    padding: 0px 64px 0px 4px;
                    }

                    td.classifications {
                    font-size: 14px;
                    font-weight: bold;
                    text-align: left;
                    vertical-align: middle;
                    padding: 1px 0px 1px 0px;
                    color: #003366
                    }

                    td.classification {
                    font-size: 11px;
                    font-weight: bold;
                    padding: 1px 0px 1px 50px;
                    color: #000000
                    }

                    td.classificationurl {
                    font-size: 11px;
                    padding: 1px 0px 1px 75px;
                    color: #000000
                    }

                    td.header {
                    font-size: 11px;
                    text-align: left;
                    vertical-align: middle;
                    padding: 1px 0px 1px 0px;
                    }

                    td.header h1 {
                    font-size: 19px;
                    font-weight: bold;
                    line-height: 23px;
                    }

                    td.subHeader {
                    font-size: 18px;
                    font-weight: bold;
                    text-align: left;
                    vertical-align: middle;
                    padding: 20px 0px 5px 0px;
                    color: #003366;
                    }

                    td.subHeader2 {
                    font-size: 18px;
                    font-weight: bold;
                    vertical-align: middle;
                    padding: 20px 0px 5px 25px;
                    color: #003366;
                    }

                    td.collapse {
                    font-size: 11px;
                    font-family: Verdana, Georgia, sans-serif
                    text-align: left;
                    vertical-align: middle;
                    padding: 0px 0px 10px 4px;
                    }

                    td.collapse2 {
                    font-size: 11px;
                    font-family: Verdana, Georgia, sans-serif
                    text-align: left;
                    vertical-align: middle;
                    padding: 0px 0px 10px 30px;
                    }

                    td.logo {
                    vertical-align: top;
                    text-align: right;
                    padding: 0px 6px 0px 0px;
                    }

                    div.para {
                    margin: 0 24px 15px 24px;
                    }

                    div.list {
                    margin-right: 24px;
                    }

                    span.ital {
                    font-style: italic;
                    }

                    span.bold {
                    font-weight: bold;
                    }

                    .customized-by {
                    font-style: italic;
                    font-size: .8em;
                    margin: 0;
                    }

                    h3 {
                    font-size: 11px;
                    font-weight: bold;
                    font-style: italic;
                    margin-left: 24px;
                    }

                    hr {
                    color: black;
                    height: 1px;
                    }

                    a.collapse:link {
                    font-size: 11px;
                    color: black;
                    text-decoration: none;
                    }

                    a.collapse:visited {
                    font-size: 11px;
                    color: black;
                    text-decoration: none;
                    }

                    a.collapse:active {
                    font-size: 11px;
                    color: black;
                    text-decoration: none;
                    }

                    ul {
                    text-indent: 40px;
                    list-style-position: outside;
                    padding: 0px 0px 0px 0px;
                    margin: 2px;
                    }

                    pre {
                    padding:0; margin: 0;
                    white-space: pre-wrap; /* css-3 */
                    white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
                    white-space: -pre-wrap; /* Opera 4-6 */
                    white-space: -o-pre-wrap; /* Opera 7 */
                    word-wrap: break-word; /* Internet Explorer 5.5+ */
                    }
                </style>
            </head>
            <body>

                <!-- SHORT DESCRIPTION -->
                <xsl:apply-templates select="ShortDescription"/>

                <!-- LONG DESCRIPTION -->
                <xsl:apply-templates select="LongDescription"/>

                <!-- CLASSIFICATIONS -->
                <xsl:apply-templates select="Classifications"/>
            </body>
        </html>
    </xsl:template>

    <!-- Classifications -->
    <xsl:template match="Classifications">
        <table class="subHeader">
            <tr>
                <td class="subHeader">Classifications</td>
            </tr>
            <xsl:for-each select="Classification">
                <tr>
                    <td class="classification" rowspan="1" colspan="2">
                        <xsl:value-of select="@source"/>
                        -
                        <xsl:value-of select="Name"/>
                    </td>
                </tr>
                <tr>
                    <td class="classificationurl" rowspan="1" colspan="2">
                        <xsl:variable name="url" select="URL"></xsl:variable>
                        <a href="{$url}">
                            <xsl:value-of select="URL"/>
                        </a>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>

    <!--  Short Description -->
    <xsl:template match="ShortDescription">
        <table class="subHeader">
            <tr>
                <td class="subHeader">Brief Description</td>
            </tr>
            <tr>
                <td class="collapse">
                    <div class="para" id="short">
                        <xsl:apply-templates/>
                    </div>
                </td>
            </tr>
        </table>
    </xsl:template>

    <!--  Long Description -->
    <xsl:template match="LongDescription">
        <table class="subHeader">
            <tr>
                <td class="subHeader">Extended Description</td>
            </tr>
            <tr>
                <td class="collapse">
                    <div class="para" id="long">
                        <xsl:apply-templates/>
                    </div>
                </td>
            </tr>
        </table>
    </xsl:template>


    <!-- Description template -->
    <xsl:template match="Description" name="Description">
        <div class="para" id="short">
            <xsl:apply-templates/>
        </div>
    </xsl:template>

    <!--  References -->
    <xsl:template match="References">
        <table class="subHeader">
            <tr>
                <td class="subHeader">References</td>
            </tr>
        </table>

        <xsl:apply-templates/>
    </xsl:template>


    <!--  Reference -->
    <xsl:template match="Reference">

        <div class="para">
            <xsl:choose>
                <xsl:when test='@type = "External"'>
                    <strong>External</strong>
                </xsl:when>
                <xsl:when test='@type = "Internal"'>
                    <strong>Internal</strong>
                </xsl:when>
                <xsl:when test='@type = "RelatedStandard"'>
                    <strong>Related Standard</strong>
                </xsl:when>
            </xsl:choose>

            <xsl:variable name="url" select="URL"></xsl:variable>
            <div class="para">
                <a href="{$url}">
                    <xsl:value-of select="URL"/>
                </a>
                <div class="para">
                    <xsl:apply-templates/>
                </div>
            </div>
        </div>
    </xsl:template>

    <!--  URL -->
    <xsl:template match="URL" name="URL"/>

    <!-- Title -->
    <xsl:template match="Title"/>

    <!-- Paragraph -->
    <xsl:template match='br'>
        <br/>
    </xsl:template>

    <!-- Paragraph -->
    <xsl:template match='p'>
        <p>
            <xsl:apply-templates/>
        </p>
    </xsl:template>

    <!-- Italic -->
    <xsl:template match='i'>
        <i>
            <xsl:apply-templates/>
        </i>
    </xsl:template>

    <!-- Unordered List -->
    <xsl:template match='ul'>
        <ul>
            <xsl:apply-templates/>
        </ul>
    </xsl:template>

    <!-- Header 1 -->
    <xsl:template match='h1'>
        <h1>
            <xsl:apply-templates/>
        </h1>
    </xsl:template>

    <!-- Bold -->
    <xsl:template match='b'>
        <b>
            <xsl:apply-templates/>
        </b>
    </xsl:template>

    <!-- Underscored -->
    <xsl:template match='u'>
        <u>
            <xsl:apply-templates/>
        </u>
    </xsl:template>

    <!-- List item -->
    <xsl:template match='li'>
        <li>
            <xsl:apply-templates/>
        </li>
    </xsl:template>

    <!-- Link item -->
    <xsl:template match='a'>
        <xsl:variable name="url" select="@href"></xsl:variable>
        <a href="{$url}">
            <xsl:value-of select="."/>
        </a>
    </xsl:template>

</xsl:stylesheet>