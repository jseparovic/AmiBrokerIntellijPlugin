package com.jseppa.intellij.amibroker;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.jseppa.intellij.amibroker.psi.AFLTypes;
import com.intellij.psi.TokenType;

%%

%class AFLLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

line_terminator = \r|\n|\r\n
white_space_char = [ \t\f]

letter = [:letter:]
digit = [:digit:]

identifier = (_|{letter}) (_|{digit}|{letter})*

block_comment = "/*" {block_comment_content} "*/"
block_comment_content = ([^*] | "*"+ [^/])*
line_comment = "/""/"[^\r\n]* {line_terminator}?

double_quoted_string = \" ( [^\\\"] |{escape_sequence})* \" {string_postfix}?
string_postfix = [cwd]

escape_sequence = {escape_sequence_spec_char}
escape_sequence_spec_char = "\\\'" | "\\\"" | "\\\?" | "\\\\" | "\\0" | "\\a" | "\\b"  | "\\f"  | "\\n"  | "\\r"  | "\\t" | "\\v"

char_literal = \' ( [^\r\n\t\f\\] | {escape_sequence} ) \'
integer_literal = ({decimal_integer} | {hexadecimal_integer}) {integer_suffix}?

integer_suffix =  L | u | U | Lu | LU | uL | UL
decimal_integer = 0 | ([1-9] [0-9]*)
hexadecimal_integer = 0[xX] [0-9a-fA-F] [0-9a-fA-F]*

float_literal = {decimal_float}
decimal_float = ( {decimal_float_simple} | {decimal_float_exponent} | {decimal_float_no_dot_exponent} )
decimal_float_simple = [0-9]* \. ([0-9]+)
decimal_float_exponent = [0-9]* \. [0-9_]+ {decimal_exponent}
decimal_float_no_dot_exponent = [0-9]+ {decimal_exponent}
decimal_exponent = [eE][\+\-]? [0-9_]+

color_prefix = color
color_name= Black | Brown | DarkOliveGreen | DarkGreen | DarkTeal | DarkBlue | Indigo | DarkGrey |DarkRed | Orange | DarkYellow |
                Green | Teal | Blue | BlueGrey | Grey40 |Red | LightOrange | Lime | SeaGreen | Aqua | LightBlue | Violet | Grey50 |
                Pink | Gold | Yellow | BrightGreen | Turquoise | Skyblue | Plum | LightGrey | Rose | Tan | LightYellow | PaleGreen |
                PaleTurquoise | PaleBlue | Lavender | White | Custom1 | Custom2 | Custom3 | Custom4 | Custom5 | Custom6 | Custom7 | Custom8 |
                Custom9 | Custom10 | Custom11 | Custom12 | Custom13 | Custom14 | Custom15 | Custom16

builtin_colors = {color_prefix} {color_name}

builtin_styles = styleLine | styleHistogram | styleThick | styleDots | styleNoLine | styleLog | styleCandle | styleBar | styleNoDraw |
            styleStaircase | styleSwingDots | styleNoRescale | styleNoLabel | stylePointAndFigure | styleArea | styleOwnScale | styleLeftAxisScale

builtin_headers = #include | #include_once | #pragma

builtin_functions = abs | AccDist | acos | AddColumn | AddMultiTextColumn | AddRankColumn | AddRow
                        | AddSummaryRows | AddTextColumn | AddToComposite | ADLine | AdvIssues | AdvVolume | ADX | AlertIf | AlmostEqual
                        | AMA | AMA2 | ApplyStop | Asc | asin | atan | atan2 | ATR | BarIndex | BarsSince | BBandBot | BBandTop | BeginValue
                        | CategoryAddSymbol | CategoryCreate | CategoryFind | CategoryGetName | CategoryGetSymbols | CategoryRemoveSymbol
                        | CategorySetName | CCI | ceil | Chaikin | ClipboardGet | ClipboardSet | ColorBlend | ColorHSB | ColorRGB
                        | Correlation | cos | cosh | CreateObject | CreateStaticObject | Cross | Cum | CumProd | Date | DateNum | DateTime
                        | DateTimeAdd | DateTimeConvert | DateTimeDiff | DateTimeFormat | DateTimeToStr | Day | DayOfWeek | DayOfYear
                        | DaysSince1900 | DecIssues | DecVolume | DEMA | EMA | EnableRotationalTrading | EnableScript | EnableTextOutput
                        | EncodeColor | EndValue | Equity | Error | EXP | ExRem | ExRemSpan | fclose | fdelete | fdir | feof | FFT | fgetcwd
                        | fgets | fgetstatus | FIR | FirstVisibleValue | Flip | floor | fmkdir | fopen | Foreign | fputs | frac | frmdir
                        | FullName | GapDown | GapUp | GetAsyncKeyState | GetBacktesterObject | GetBaseIndex | GetCategorySymbols
                        | GetChartBkColor | GetChartID | GetCursorMouseButtons | GetCursorXPosition | GetCursorYPosition | GetDatabaseName
                        | GetExtraData | GetExtraDataForeign | GetFnData | GetFnDataForeign | GetFormulaPath | GetLastOSError | GetOption
                        | GetPerformanceCounter | GetPlaybackDateTime | GetPriceStyle | GetRTData | GetRTDataForeign | GetScriptObject
                        | GetTradingInterface | GfxArc | GfxChord | GfxCircle | GfxDrawImage | GfxDrawText | GfxEllipse | GfxFillSolidRect
                        | GfxGetTextWidth | GfxGradientRect | GfxLineTo | GfxMoveTo | GfxPie | GfxPolygon | GfxPolyline | GfxRectangle
                        | GfxRoundRect | GfxSelectFont | GfxSelectHatchBrush | GfxSelectPen | GfxSelectSolidBrush | GfxSelectStockObject
                        | GfxSetBkColor | GfxSetBkMode | GfxSetCoordsMode | GfxSetOverlayMode | GfxSetPixel | GfxSetTextAlign
                        | GfxSetTextColor | GfxSetZOrder | GfxTextOut | GicsID | GroupID | GuiButton | GuiCheckBox | GuiDateTime | GuiEdit
                        | GuiEnable | GuiGetCheck | GuiGetEvent | GuiGetText | GuiGetValue | GuiRadio | GuiSetCheck | GuiSetFont
                        | GuiSetRange | GuiSetText | GuiSetValue | GuiSetVisible | GuiSlider | GuiToggle | HHV | HHVBars | Highest
                        | HighestBars | HighestSince | HighestSinceBars | HighestVisibleValue | HMA | Hold | Hour | IcbID | IIf | IIR
                        | IndustryID | InGICS | InICB | Inside | Int | InternetClose | InternetOpenURL | InternetReadString | InternetSetAgent
                        | Interval | InWatchList | InWatchListName | IsContinuous | IsEmpty | IsFavorite | IsFinite | IsIndex | IsNan | IsNull
                        | IsTrue | Kurtosis | LastValue | LastVisibleValue | LineArray | LinearReg | LinRegIntercept | LinRegSlope | LLV
                        | LLVBars | log | log10 | Lookup | Lowest | LowestBars | LowestSince | LowestSinceBars | LowestVisibleValue | MA
                        | MACD | MarketID | Matrix | Max | MDI | Median | MFI | MicroSec | MilliSec | Min | Minute | Month | mtRandom
                        | mtRandomA | MxDet | MxFromString | MxGetBlock | MxGetSize | MxIdentity | MxInverse | MxSetBlock | MxSolve | MxSort
                        | MxSortRows | MxSum | MxToString | MxTranspose | Name | NormDist | NoteGet | NoteSet | Now | NullCount | NumToStr
                        | NVI | Nz | OBV | Optimize | OptimizerSetEngine | OptimizerSetOption | OscP | OscV | Outside | Param | ParamColor
                        | ParamDate | ParamField | ParamList | ParamStr | ParamStyle | ParamTime | ParamToggle | ParamTrigger | PDI | Peak
                        | PeakBars | Percentile | PercentRank | PlaySound | Plot | PlotForeign | PlotGrid | PlotOHLC | PlotShapes | PlotText
                        | PlotTextSetFont | PlotVAPOverlay | PlotVAPOverlayA | PopupWindow | Prec | Prefs | PriceVolDistribution | printf
                        | Prod | ProdSince | PVI | Random | Ref | RelStrength | Remap | RequestMouseMoveRefresh | RequestTimedRefresh
                        | RestorePriceArrays | Reverse | RMI | ROC | Round | RSI | RWI | RWIHi | RWILo | SAR | Say | Second | SectorID
                        | SelectedValue | SendEmail | SetBacktestMode | SetBarFillColor | SetBarsRequired | SetChartBkColor
                        | SetChartBkGradientFill | SetChartOptions | SetCustomBacktestProc | SetForeign | SetFormulaName | SetGradientFill
                        | SetOption | SetPositionSize | SetSortColumns | SetStopPrecedence | SetTradeDelays | ShellExecute | sign | Signal
                        | sin | sinh | Skewness | Sort | SparseCompress | SparseExpand | sqrt | StaticVarAdd | StaticVarCompareExchange
                        | StaticVarCount | StaticVarGenerateRanks | StaticVarGet | StaticVarGetRankedSymbols | StaticVarGetText | StaticVarInfo
                        | StaticVarRemove | StaticVarSet | StaticVarSetText | Status | StdErr | StDev | StochD | StochK | StrCount | StrExtract
                        | StrFind | StrFormat | StrLeft | StrLen | StrMatch | StrMid | StrReplace | StrRight | StrSort | StrToDateTime
                        | StrToLower | StrToNum | StrToUpper | StrTrim | Study | Sum | SumSince | tan | tanh | TEMA | ThreadSleep
                        | TimeFrameCompress | TimeFrameExpand | TimeFrameGetPrice | TimeFrameMode | TimeFrameRestore | TimeFrameSet | TimeNum
                        | Trin | TRIX | Trough | TroughBars | TSF | Ultimate | UncIssues | UncVolume | ValueWhen | VarGet | VarGetText
                        | VarSet | VarSetText | Version | VoiceCount | VoiceSelect | VoiceSetRate | VoiceSetVolume | VoiceWaitUntilDone
                        | Wilders | WMA | WriteIf | WriteVal | XYChartAddPoint | XYChartSetAxis | Year | ZIG | _DEFAULT_NAME | _DT | _N
                        | _PARAM_VALUES | _SECTION_BEGIN | _SECTION_END | _SECTION_NAME | _TRACE | _TRACEF

builtin_arrays = Open | High | Low | Close | Volume | Buy | Sell | Short | Cover | BuyPrice | SellPrice | ShortPrice | CoverPrice


/*End of rules*/


%%
<YYINITIAL> {

{line_terminator}+  { return AFLTypes.LINE_TERMINATOR; }
{white_space_char}+ { return AFLTypes.WHITE_SPACE; }

{block_comment}     { return AFLTypes.BLOCK_COMMENT; }
{line_comment}      { return AFLTypes.LINE_COMMENT; }

{char_literal} { return AFLTypes.CHAR_LITERAL; }
{integer_literal} { return AFLTypes.INTEGER_LITERAL; }
{float_literal} { return AFLTypes.DOUBLE_LITERAL; }
{double_quoted_string} { return AFLTypes.STRING_LITERAL; }

{builtin_colors} { return AFLTypes.BUILTIN_COLORS; }
{builtin_headers} { return AFLTypes.BUILTIN_HEADERS; }
{builtin_functions} { return AFLTypes.BUILTIN_FUNCTIONS; }
{builtin_arrays} { return AFLTypes.BUILTIN_ARRAYS; }
{builtin_styles} { return AFLTypes.BUILTIN_STYLES; }



// Keywords
"if"        { return AFLTypes.IF_KEYWORD; }
"else"      { return AFLTypes.ELSE_KEYWORD; }
"for"       { return AFLTypes.FOR_KEYWORD; }
"while"     { return AFLTypes.WHILE_KEYWORD; }
"local"     { return AFLTypes.LOCAL_KEYWORD; }
"break"     { return AFLTypes.BREAK_KEYWORD; }
"switch"    { return AFLTypes.SWITCH_KEYWORD; }
"case"      { return AFLTypes.CASE_KEYWORD; }
"continue"  { return AFLTypes.CONTINUE_KEYWORD; }
"default"   { return AFLTypes.DEFAULT_KEYWORD; }
"do"        { return AFLTypes.DO_KEYWORD; }
"function"  { return AFLTypes.FUNCTION_KEYWORD; }
"global"    { return AFLTypes.GLOBAL_KEYWORD; }
"procedure" { return AFLTypes.PROCEDURE_KEYWORD; }
"return"    { return AFLTypes.RETURN_KEYWORD; }
"static"    { return AFLTypes.STATIC_KEYWORD; }
"typeof"    { return AFLTypes.TYPEOF_KEYWORD; }

// Logical
"NOT"    { return AFLTypes.LOGICAL_NOT; }
"AND"    { return AFLTypes.LOGICAL_AND; }
"OR"     { return AFLTypes.LOGICAL_OR; }


{identifier}     { return AFLTypes.IDENTIFIER; }


// Operators & special characters

"++"     { return AFLTypes.INCREMENT; }
"--"     { return AFLTypes.DECREMENT; }
"[]"     { return AFLTypes.ARRAY_ELEMENT; }
"^"      { return AFLTypes.EXP; }
"-"      { return AFLTypes.MINUS; }
"*"      { return AFLTypes.MUL; }
"/"      { return AFLTypes.DIV; }
"%"      { return AFLTypes.MOD; }
"+"      { return AFLTypes.PLUS; }
"<"      { return AFLTypes.LT; }
">"      { return AFLTypes.GT; }
"=="     { return AFLTypes.EQ_EQ; }
"<="     { return AFLTypes.LESS_EQ; }
">="     { return AFLTypes.GT_EQ; }
"!="     { return AFLTypes.NOT_EQ; }
"&"      { return AFLTypes.BITWISE_AND; }
"|"      { return AFLTypes.BITWISE_OR; }
"="      { return AFLTypes.EQ; }



"*="      { return AFLTypes.ASSIGN_MUL; }
"/="      { return AFLTypes.ASSIGN_DIV; }
"%="      { return AFLTypes.ASSIGN_MOD; }
"+="      { return AFLTypes.ASSIGN_PLUS; }
"-="      { return AFLTypes.ASSIGN_MINUS; }
"&="      { return AFLTypes.ASSIGN_AND; }
"|="      { return AFLTypes.ASSIGN_OR; }


"("   { return AFLTypes.L_ROUND_BRACKET; }
")"   { return AFLTypes.R_ROUND_BRACKET; }
"{"   { return AFLTypes.L_CURLY_BRACKET; }
"}"   { return AFLTypes.R_CURLY_BRACKET; }
"["   { return AFLTypes.L_SQUARE_BRACKET; }
"]"   { return AFLTypes.R_SQUARE_BRACKET; }
";"   { return AFLTypes.SEMICOLON; }
":"   { return AFLTypes.COLON; }
","   { return AFLTypes.COMMA; }
"."   { return AFLTypes.DOT; }
}

[^] { return AFLTypes.BAD_CHARACTER; }
