if1_in :: FromDevice()
if2_in :: FromDevice()
if3_in :: FromDevice()
if1_out :: ToDevice()
if2_out :: ToDevice()
if3_out :: ToDevice()

class :: IPClassifier(dst net 192.168.0.0/24, -)

if1_in -> EtherDecap() -> EtherEncap(2048, 0000.0000.0000, ffff.ffff.ffff) -> if3_out
if2_in -> EtherDecap() -> EtherEncap(2048, 0000.0000.0000, ffff.ffff.ffff) -> if3_out

if3_in -> EtherDecap() -> class

class[0] -> EtherEncap(2048, 0000.0000.0000, aaaa.aaaa.aaaa) -> if1_out
class[1] -> EtherEncap(2048, 0000.0000.0000, cccc.cccc.cccc) -> if2_out
