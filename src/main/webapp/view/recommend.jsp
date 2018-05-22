<%@ page import="com.yan.crawler.data.Track" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%--<script src="../resource/js/bootstrap.js"/>--%>
    <%--<script src="../resource/js/bootstrap.min.js"/>--%>
    <link rel="stylesheet" type="text/css" href="../resource/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../resource/css/bootstrap-theme.css"/>
</head>
<body>
<div id="g_main" class="g_main g_col17 g_mainManage"
     style="top:51px;background:  0;position:  absolute;min-height:  0;left:  0;right:  0;bottom:  0;margin: 0;font-size:  14px;font-weight:  normal;font-family:  微软雅黑;color:  #222222;">
    <div id="web" class="g_web g_webManage" style="width: 100%;padding:  0;margin:  0;">
        <table class="webTopTable" cellpadding="0" cellspacing="0" style="
    width:  100%;
    table-layout:  fixed;
    margin:  0;
    padding:  0;
    border-spacing:  0;
    height:  0;
    font-size:  0;
    display:  table;
    border-color:  grey;
    border-collapse:  separate;
">
            <tbody>
            <tr>
                <td align="center">
                    <div id="webTop" class="webTop" style="
    width:  1200px;
    z-index:  31;
    position:  relative;
    margin:  0;
    padding:  0;
    height:  0;
    font-size:  0;
    display:  block;
    color:  #222222;
">
                        <div id="corpTitle" class="corpTitle corpTitle2 ui-draggable" fontpatterntitle="false"
                             style="min-height: 40px;min-width: 160px;height: auto;width: auto;top: 35px;left:  1px;position:  absolute;font-size:  32px;z-index:  32;text-align:  left;white-space: nowrap;"
                             _linktype="0">
                            <div class="fkEditor-wrap">
                                <div id="fkEditor-corpTitle" class="fk-editor fkEditor "
                                     style="width: auto; height: auto; border: 0px; min-height: 40px; min-width: 160px;"
                                     _width="auto" _height="auto"
                                     onpaste="Site.simpleTextFormatPasteData(event, 'corpTitle')"
                                     contenteditable="false">
                                    <div class="newPrimaryTitle"><span style="color: rgb(230, 74, 25);"><b>基于Hadoop的音乐推荐系统</b></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="logo"
                             class="logo logo2 ui-draggable ui-resizable ui-resizable-autohide ui-resizable-disabled ui-state-disabled"
                             style="display: none; cursor: move; overflow: visible; text-align: left;"
                             onclick="Site.editLogoPicFunc();" _defaultlogo="1" aria-disabled="true"><a id="logoLink"
                                                                                                        hidefocus="true"
                                                                                                        class="link-p"
                                                                                                        link="javascript:;"
                                                                                                        onclick="return false;"><img
                                id="logoImg" title="-" alt="-" style="float: left; padding: 0px;"
                                src="resource/img/tj-logo.png"></a><a href="javascript:;"
                                                                      class="J_resizableTip ui-resizable-cancelTip fk-resizable-cancelTip"
                                                                      title="还原LOGO初始大小"
                                                                      onclick="Site.dockLogoAutoSize();return false;"
                                                                      style="display: none;"></a>
                            <div class="ui-resizable-handle ui-resizable-n ui-resizable-imgTopTip ui-resizable-floatImgTop"
                                 style="z-index: 5; display: none; top: -4px;">
                                <div class="imgResizeLine imgResizeLine-first" style="width: 51px;"></div>
                                <div class="imgResizeLine imgResizeLine-second" style="width: 51px;"></div>
                            </div>
                            <div class="ui-resizable-handle ui-resizable-e ui-resizable-imgRightTip ui-resizable-floatImgRight"
                                 style="z-index: 5; display: none; right: -4px;">
                                <div class="imgResizeLine imgResizeLine-first" style="height: 51px;"></div>
                                <div class="imgResizeLine imgResizeLine-second" style="height: 51px;"></div>
                            </div>
                            <div class="ui-resizable-handle ui-resizable-s ui-resizable-imgBottomTip ui-resizable-floatImgBottom"
                                 style="z-index: 5; display: none; bottom: -4px;">
                                <div class="imgResizeLine imgResizeLine-first" style="width: 51px;"></div>
                                <div class="imgResizeLine imgResizeLine-second" style="width: 51px;"></div>
                            </div>
                            <div class="ui-resizable-handle ui-resizable-w ui-resizable-imgLeftTip ui-resizable-floatImgLeft"
                                 style="z-index: 5; display: none; left: -4px;">
                                <div class="imgResizeLine imgResizeLine-first" style="height: 51px;"></div>
                                <div class="imgResizeLine imgResizeLine-second" style="height: 51px;"></div>
                            </div>
                            <div class="ui-resizable-handle ui-resizable-ne ui-resizable-imgTopRightTip ui-resizable-floatImgTopRight"
                                 style="z-index: 5; display: none; top: -9px; right: -9px;"></div>
                            <div class="ui-resizable-handle ui-resizable-nw ui-resizable-imgTopLeftTip ui-resizable-floatImgTopLeft"
                                 style="z-index: 5; display: none; top: -9px; left: -9px;"></div>
                            <div class="ui-resizable-handle ui-resizable-sw ui-resizable-imgBottomLeftTip ui-resizable-floatImgBottomLeft"
                                 style="z-index: 5; display: none; bottom: -9px; left: -9px;"></div>
                            <div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-resizable-imgBottomRightTip ui-resizable-floatImgBottomRight"
                                 style="z-index: 5; display: none; bottom: -9px; right: -9px;"></div>
                        </div>

                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <table class="webHeaderTable J_webHeaderTable" cellpadding="0" cellspacing="0" id="webHeaderTable" style="
    display:  table;
    height:  120px;
    width:  100%;
    table-layout:  fixed;
    margin:  0;
    padding:  0;
    border-spacing:  0;
    border-collapse:  separate;
    border-color:  grey;
    font-size:  14px;
    font-weight: normal;
">
            <tbody>
            <tr>
                <td align="center" class="J_webHeaderTable webHeaderTd fk-moduleZoneWrap" style="
    height:  120px;
    vertical-align:  bottom;
    position:  relative;
    text-align:  -webkit-center;
    border-collapse:  separate;
">

                    <div id="fk-webHeaderZone"
                         class="elemZone elemZoneModule J_moduleZone fk-webHeaderZone fk-moduleZone forms sideForms ui-sortable"
                         style="
    width:  1200px;
    margin-left:  -600.0px;
    position:  absolute;
    height:  inherit;
    overflow:  hidden;
    flex: 1;
    top: 0;
    left:  50%;
    bottom:  0;
">
                        <div class="fk-moduleZoneBg fk-elemZoneBg J_zoneContentBg elemZoneBg"></div>

                    </div>

                    <div id="webHeader" class="webHeader"
                         style="width:  1200px;z-index:  1;position:  relative;margin:  0;padding:  0;">
                        <table class="headerTable" cellpadding="0" cellspacing="0" style="
    table-layout:  fixed;
    width:  100%;
    height:  100%;
    margin-left:600px;
">
                            <tbody>
                            <tr>
                                <td class="headerCusLeft"></td>
                                <td class="headerCusMiddle" align="center" valign="top" style="
    width:  100%;
    height:  100%;
">
                                    <div class="headerNav ">
                                        <div id="nav" class="nav nav2 fixedNavPos  ui-draggable ui-resizable" style="
    border-style:  none;
    height:  120px;
    top: auto;
    bottom:  0;
    z-index:  30;
">
                                            <div class="navMainContent" style="
    width:  1200px;
    height:  100%;
">
                                                <table class="navTop" cellpadding="0" cellspacing="0">
                                                    <tbody>
                                                    <tr>
                                                        <td class="navTopLeft"></td>
                                                        <td class="navTopCenter"></td>
                                                        <td class="navTopRight"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <table class="navContent" cellpadding="0" cellspacing="0" style="
    background-size:  contain;
    height:  120px;
    clear:  both;
    padding:  0;
    border-spacing:  0;
    table-layout:  fixed;
    margin:  auto;
    width:  100%;
    display:  table;
">
                                                    <tbody>
                                                    <tr>
                                                        <td class="navLeft"></td>
                                                        <td class="navCenterContent" id="navCenterContent" valign="top"
                                                            align="left" style="
    background:  none;
    height:  100%;
    margin:  0;
    padding:  0;
    width:  100%;
    vertical-align:  top;
    text-align:  -webkit-left;
    display:  table-cell;
">
                                                            <div id="navCenter" class="navCenter"
                                                                 style="width: 292px;background:  none;position:  relative;margin:  0;padding:  0;overflow:  hidden;height:  100%;">
                                                                <div class="itemPrev" style="display: none;"></div>
                                                                <div class="itemContainer" style="
    position:  absolute;
    top: 0;
    left:  0;
    margin:  0;
    padding:  0;
    overflow: hidden;
    height:  auto;
">
                                                                    <table title="" class="item itemCol2 itemIndex1"
                                                                           cellpadding="0" cellspacing="0" colid="2"
                                                                           id="nav2" style="
    height:  120px;
    width:  140px;
    position:  relative;
    float:  left;
">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td class="itemLeft" style="
    width:  0px;
"></td>
                                                                            <td class="itemCenter"><a hidefocus="true"
                                                                                                      style="outline:none;margin-top: 0;margin-bottom:  0;padding-top: 0;padding-bottom:  0;margin-left:  0;margin-right:  0;padding-left:  0;padding-right:  0;text-decoration:  none;font-size:  16px;font-weight:  normal;font-family: 微软雅黑;color:  #2b2b2b;text-shadow:  none;text-align:  center;display:  block;word-break:  keep-all;white-space:  nowrap;width:  auto;min-width:  60px;max-width:  none;"
                                                                                                      href="http://sf13265748.jz.fkw.com/"
                                                                                                      target="_self"
                                                                                                      onclick="return false;"><span
                                                                                    class="itemName0">发现音乐</span></a>
                                                                            </td>
                                                                            <td class="itemRight"></td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                    <div class="itemSep" style="
    background:  none;
    width:  2px;
    display:  inline;
    height:  120px;
    margin-top: 0px;
    float:  left;
    position:  relative;
"></div>
                                                                    <table title="" class="item itemCol103 itemIndex2"
                                                                           cellpadding="0" cellspacing="0" colid="103"
                                                                           id="nav103" style="
    height:  120px;
    width:  140px;
    float:  left;
">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td class="itemLeft"></td>
                                                                            <td class="itemCenter"><a hidefocus="true"
                                                                                                      style="outline:none;font-size:  16px;color:  #2b2b2b;text-align:  center;min-width:  60px;text-decoration:  none;"
                                                                                                      href="http://localhost:8080/MusicRecommenderSystem/favor"><span
                                                                                    class="itemName0">我的音乐</span></a>
                                                                            </td>
                                                                            <td class="itemRight"></td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="itemNext" style="display: none;"></div>
                                                            </div>
                                                        </td>
                                                        <td class="navRight"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <table class="navBottom" cellpadding="0" cellspacing="0">
                                                    <tbody>
                                                    <tr>
                                                        <td class="navBottomLeft"></td>
                                                        <td class="navBottomCenter"></td>
                                                        <td class="navBottomRight"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="ui-resizable-handle ui-resizable-n"
                                                 style="z-index: 1000; display: none;"></div>
                                            <div class="ui-resizable-handle ui-resizable-e"
                                                 style="z-index: 1000; display: none;"></div>
                                            <div class="ui-resizable-handle ui-resizable-s"
                                                 style="z-index: 1000; display: none;"></div>
                                            <div class="ui-resizable-handle ui-resizable-w"
                                                 style="z-index: 1000; display: none;"></div>
                                            <div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-resizable-rightTip"
                                                 _faiscotip="按住鼠标，可随意调整大小。" style="z-index: 1000; display: none;"></div>
                                            <div class="ui-resizable-handle ui-resizable-sw ui-icon ui-icon-gripsmall-diagonal-ns ui-resizable-leftTip"
                                                 _faiscotip="按住鼠标，可随意调整大小。" style="z-index: 1000; display: none;"></div>
                                        </div>

                                    </div>
                                </td>
                                <td class="headerCusRight"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>


    </div>
    <%
        List<Track> recommend = (ArrayList) request.getAttribute("recommend");
    %>
    <div>
        <table border="0" width="80%" align="center">
            <tr align="center" valign="top">
                <td height="37" colspan="9"><h2 align="center">推荐音乐</h2></td>
            </tr>
            <tr valign="top">
                <td height="258" width="100%">
                    <table align="center" width="100%" border="1" id="planTable2"
                           cellpadding="1" cellspacing="0">
                        <tr align="center" bgcolor="#e64a19" onMouseOver="">

                            <td width="40%">artist</td>
                            <td width="40%">name</td>
                            <td width="20%">url</td>

                        </tr>
                        <%
                            for (int i = 0; i < recommend.size(); i++) {
                                Track track = recommend.get(i);
                        %>
                        <tr align="center" height="35px">
                            <td style="background-color:#f5b6a2"><%=track.getArtist() %>
                            </td>
                            <td style="background-color:#f5b6a2"><%=track.getName() %>
                            </td>
                            <td style="background-color:#f5b6a2">
                                <button style="height: 30px;padding:5px;font-size:2px" class="btn btn-default btn-lg"
                                        onclick="window.location.href='<%=track.getUrl() %>'"><span
                                        class="glyphicon glyphicon-play-circle"></span>试听
                                </button>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </td>
            </tr>


            <tr>
                <td width="6%" height="19">&nbsp;</td>

                <td width="6%">&nbsp;</td>
                <td width="11%">&nbsp;</td>
                <td width="11%">&nbsp;</td>
                <td width="14%">&nbsp;</td>
            </tr>

            <tr>
                <td colspan="9"></td>
        </table>
    </div>
</div>
</body>
</html>