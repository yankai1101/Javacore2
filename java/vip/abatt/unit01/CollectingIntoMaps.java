package vip.abatt.unit01;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author:YANKAI_1101
 * Date:2020/3/24
 * Desc：将流的结果集映射到映射表中
 **/
public class CollectingIntoMaps {
    public static void main(String[] args) {
        // 用来产生一个映射的key-value 默认 HashMap
        Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName.getClass().getName() + idToName); // idToName: java.util.HashMap{1001=A, 1002=B, 1003=B}

        // 用来产生一个映射的 key-object 默认 HashMap
        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson); // idToPerson: java.util.HashMap{1001=Person{id=1001, name='A'}, 1002=Person{id=1002, name='B'}, 1003=Person{id=1003, name='C'}}

        // 用来产生一个映射的 key-object 类型为TreeMap的集合
        idToPerson = people().collect(
                Collectors.toMap(Person::getId, Function.identity(),
                        (existingValue, newValue) -> {
                            throw new IllegalStateException();
                        },
                        TreeMap::new
                ));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson); // idToPerson: java.util.TreeMap{1001=Person{id=1001, name='A'}, 1002=Person{id=1002, name='B'}, 1003=Person{id=1003, name='C'}}


        // 用来产生一个映射的key-value,key是语言名，value是语言
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(
                Collectors.toMap(Locale::getDisplayLanguage,
                        locale -> locale.getDisplayLanguage(locale),
                        (existingValue, newValue) -> existingValue
                ));
        System.out.println("languageNames: " + languageNames); // languageNames: {旺杜语=ewondo, 乌兹别克语=ўзбекча, =, 桑布鲁语=Kisampur, 隆迪语=Ikirundi, 索加语=Olusoga, ccp=𑄌𑄋𑄴𑄟𑄳𑄦, 克罗地亚语=hrvatski, 尼昂科勒语=Runyankore, 亚美尼亚语=հայերեն, 瑞士德语=Schwiizertüütsch, 克什米尔语=کٲشُر, 马其顿语=македонски, 夏威夷语=ʻŌlelo Hawaiʻi, 夸西奥语=nmg, 马切姆语=Kimachame, 班巴拉语=bamanakan, 阿萨姆语=অসমীয়া, 瓦伊语=Vai, 阿塞拜疆语=azərbaycan, 切罗基语=ᏣᎳᎩ, 卡拜尔语=Taqbaylit, 塞尔维亚语=srpski, 格陵兰语=kalaallisut, 波斯尼亚语=bosanski, 下索布语=dolnoserbšćina, 马恩语=Gaelg, 鲁巴加丹加语=Tshiluba, 北方萨米语=davvisámegiella, 中库尔德语=کوردیی ناوەندی, 卢干达语=Luganda, 科隆语=Kölsch, 亚罕语=Aghem, 书面挪威语=norsk bokmål, 富拉语=Pulaar, 泰米尔语=தமிழ், 车臣语=нохчийн, 古西语=Ekegusii, 兰博语=Kihorombo, 阿尔巴尼亚语=shqip, 伊纳里萨米语=anarâškielâ, 标准摩洛哥塔马塞特语=ⵜⴰⵎⴰⵣⵉⵖⵜ, 西弗里西亚语=Frysk, 白俄罗斯语=беларуская, 爱尔兰语=Gaeilge, 都阿拉语=duálá, 毛里求斯克里奥尔语=kreol morisien, 缅甸语=မြန်မာ, 努埃尔语=Thok Nath, 越南语=Tiếng Việt, 旁遮普语=ਪੰਜਾਬੀ, 法罗语=føroyskt, 俄语=русский, 日语=日本語, 土耳其语=Türkçe, 蒙当语=MUNDAŊ, 豪萨语=Hausa, 罗瓦语=Kiruwa, 康沃尔语=kernewek, 孔卡尼语=कोंकणी, 祖鲁语=isiZulu, 朱拉语=joola, 南非荷兰语=Afrikaans, 马来语=Melayu, 捷克语=čeština, 巴萨语=Ɓàsàa, 绍纳语=chiShona, 梅塔语=metaʼ, 吉库尤语=Gikuyu, 乌尔都语=اردو, 东桑海语=Koyraboro senni, 马库阿语=Makua, 北恩德贝勒语=isiNdebele, 布列塔尼语=brezhoneg, 菲律宾语=Filipino, 哈萨克语=қазақ тілі, 马拉地语=मराठी, 马孔德语=Chimakonde, 泰卢固语=తెలుగు, 泰语=ไทย, 中文=中文, 四川彝语=ꆈꌠꉙ, 特索语=Kiteso, 塔马齐格特语=Tamaziɣt n laṭlaṣ, 卡姆巴语=Kikamba, 拉科塔语=Lakȟólʼiyapi, 保加利亚语=български, 恩布语=Kĩembu, 马赞德兰语=مازرونی, 桑古语=Ishisangu, 鞑靼语=татар, 奥里亚语=ଓଡ଼ିଆ, 马赛语=Maa, 英语=English, 汤加语=lea fakatonga, 阿姆哈拉语=አማርኛ, 斯洛文尼亚语=slovenščina, 沃拉普克语=Volapük, 纳马语=Khoekhoegowab, 德语=Deutsch, 罗曼什语=rumantsch, 蒙古语=монгол, 塞纳语=sena, 埃维语=Eʋegbe, 奥罗莫语=Oromoo, 波斯语=فارسی, 本巴语=Ichibemba, 僧伽罗语=සිංහල, 格鲁吉亚语=ქართული, 卡纳达语=ಕನ್ನಡ, 韩语=한국어, 丹麦语=dansk, 洋卞语=nuasue, 提格利尼亚语=ትግርኛ, 巴菲亚语=rikpa, 巴斯克语=euskara, 教会斯拉夫语=Church Slavic, 阿拉伯语=العربية, 马耳他语=Malti, 阿斯图里亚斯语=asturianu, 斯瓦希里语=Kiswahili, 挪威尼诺斯克语=nynorsk, 梅鲁语=Kĩmĩrũ, 高棉语=ខ្មែរ, 卢奥语=Dholuo, 台塔语=Kitaita, 拉脱维亚语=latviešu, 希伯来文=עברית, 贝纳语=Hibena, 瓦尔瑟语=Walser, 低地德语=Low German, 马拉雅拉姆语=മലയാളം, 冰岛语=íslenska, 维吾尔语=ئۇيغۇرچە, 桑戈语=Sängö, 温旧语=Kyivunjo, 印地语=हिन्दी, 伊博语=Igbo, 尼泊尔语=नेपाली, 弗留利语=furlan, 印度尼西亚文=Bahasa Indonesia, 波兰语=polski, 信德语=سنڌي, 马拉加斯语=Malagasy, 依地文=Yiddish, 匈牙利语=magyar, 普什图语=پښتو, 世界语=esperanto, 希尔哈语=ⵜⴰⵛⵍⵃⵉⵜ, 奥塞梯语=ирон, 奇加语=Rukiga, 普鲁士语=prūsiskan, 加利西亚语=galego, 恩艮巴语=Ndaꞌa, 威尔士语=Cymraeg, 塔吉克语=тоҷикӣ, 索马里语=Soomaali, 葡萄牙语=português, 西桑海语=Koyra ciini, 希腊语=Ελληνικά, 卡库语=kakɔ, 克丘亚语=Runasimi, 博多语=बड़ो, 北桑海语=Tasawaq senni, 意大利语=italiano, 爱沙尼亚语=eesti, 罗马尼亚语=română, 林加拉语=lingála, 法语=français, 恩甘澎语=Shwóŋò ngiembɔɔn, 萨哈语=саха тыла, 古吉拉特语=ગુજરાતી, 加泰罗尼亚语=català, 约鲁巴语=Èdè Yorùbá, 卢旺达语=Kinyarwanda, 香巴拉语=Kishambaa, 西班牙语=español, 柯尔克孜语=кыргызча, 荷兰语=Nederlands, 卡伦金语=Kalenjin, 卢森堡语=Lëtzebuergesch, 粤语=粤语, 挪威语=norsk, 北卢尔语=لۊری شومالی, 土库曼语=türkmen dili, 朗吉语=Kɨlaangi, 阿肯语=Akan, 老挝语=ລາວ, 芬兰语=suomi, 帕雷语=Kipare, 卢雅语=Luluhia, 宗卡语=རྫོང་ཁ, 沃洛夫语=Wolof, 苏格兰盖尔语=Gàidhlig, 哲尔马语=Zarmaciine, 瑞典语=svenska, 孟加拉语=বাংলা, 藏语=བོད་སྐད་, 立陶宛语=lietuvių, 乌克兰语=українська, 卡布佛得鲁语=kabuverdianu, 斯洛伐克语=slovenčina, 上索布语=hornjoserbšćina}

        // 产生一个 Map<String, Set<String>> 集合
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryLanguageSets = locales.collect(
                Collectors.toMap(Locale::getDisplayLanguage,
                        locale -> Set.of(locale.getDisplayLanguage()),
                        (existingValue, newValue) -> {
                            Set<String> set = new HashSet(existingValue);
                            set.addAll(newValue);
                            return set;
                        }
                ));
        System.out.println("countryLanguageSets：" + countryLanguageSets); // countryLanguageSets：{旺杜语=[旺杜语], 乌兹别克语=[乌兹别克语], =[], 桑布鲁语=[桑布鲁语], 隆迪语=[隆迪语], 索加语=[索加语], ccp=[ccp], 克罗地亚语=[克罗地亚语], 尼昂科勒语=[尼昂科勒语], 亚美尼亚语=[亚美尼亚语], 瑞士德语=[瑞士德语], 克什米尔语=[克什米尔语], 马其顿语=[马其顿语], 夏威夷语=[夏威夷语], 夸西奥语=[夸西奥语], 马切姆语=[马切姆语], 班巴拉语=[班巴拉语], 阿萨姆语=[阿萨姆语], 瓦伊语=[瓦伊语], 阿塞拜疆语=[阿塞拜疆语], 切罗基语=[切罗基语], 卡拜尔语=[卡拜尔语], 塞尔维亚语=[塞尔维亚语], 格陵兰语=[格陵兰语], 波斯尼亚语=[波斯尼亚语], 下索布语=[下索布语], 马恩语=[马恩语], 鲁巴加丹加语=[鲁巴加丹加语], 北方萨米语=[北方萨米语], 中库尔德语=[中库尔德语], 卢干达语=[卢干达语], 科隆语=[科隆语], 亚罕语=[亚罕语], 书面挪威语=[书面挪威语], 富拉语=[富拉语], 泰米尔语=[泰米尔语], 车臣语=[车臣语], 古西语=[古西语], 兰博语=[兰博语], 阿尔巴尼亚语=[阿尔巴尼亚语], 伊纳里萨米语=[伊纳里萨米语], 标准摩洛哥塔马塞特语=[标准摩洛哥塔马塞特语], 西弗里西亚语=[西弗里西亚语], 白俄罗斯语=[白俄罗斯语], 爱尔兰语=[爱尔兰语], 都阿拉语=[都阿拉语], 毛里求斯克里奥尔语=[毛里求斯克里奥尔语], 缅甸语=[缅甸语], 努埃尔语=[努埃尔语], 越南语=[越南语], 旁遮普语=[旁遮普语], 法罗语=[法罗语], 俄语=[俄语], 日语=[日语], 土耳其语=[土耳其语], 蒙当语=[蒙当语], 豪萨语=[豪萨语], 罗瓦语=[罗瓦语], 康沃尔语=[康沃尔语], 孔卡尼语=[孔卡尼语], 祖鲁语=[祖鲁语], 朱拉语=[朱拉语], 南非荷兰语=[南非荷兰语], 马来语=[马来语], 捷克语=[捷克语], 巴萨语=[巴萨语], 绍纳语=[绍纳语], 梅塔语=[梅塔语], 吉库尤语=[吉库尤语], 乌尔都语=[乌尔都语], 东桑海语=[东桑海语], 马库阿语=[马库阿语], 北恩德贝勒语=[北恩德贝勒语], 布列塔尼语=[布列塔尼语], 菲律宾语=[菲律宾语], 哈萨克语=[哈萨克语], 马拉地语=[马拉地语], 马孔德语=[马孔德语], 泰卢固语=[泰卢固语], 泰语=[泰语], 中文=[中文], 四川彝语=[四川彝语], 特索语=[特索语], 塔马齐格特语=[塔马齐格特语], 卡姆巴语=[卡姆巴语], 拉科塔语=[拉科塔语], 保加利亚语=[保加利亚语], 恩布语=[恩布语], 马赞德兰语=[马赞德兰语], 桑古语=[桑古语], 鞑靼语=[鞑靼语], 奥里亚语=[奥里亚语], 马赛语=[马赛语], 英语=[英语], 汤加语=[汤加语], 阿姆哈拉语=[阿姆哈拉语], 斯洛文尼亚语=[斯洛文尼亚语], 沃拉普克语=[沃拉普克语], 纳马语=[纳马语], 德语=[德语], 罗曼什语=[罗曼什语], 蒙古语=[蒙古语], 塞纳语=[塞纳语], 埃维语=[埃维语], 奥罗莫语=[奥罗莫语], 波斯语=[波斯语], 本巴语=[本巴语], 僧伽罗语=[僧伽罗语], 格鲁吉亚语=[格鲁吉亚语], 卡纳达语=[卡纳达语], 韩语=[韩语], 丹麦语=[丹麦语], 洋卞语=[洋卞语], 提格利尼亚语=[提格利尼亚语], 巴菲亚语=[巴菲亚语], 巴斯克语=[巴斯克语], 教会斯拉夫语=[教会斯拉夫语], 阿拉伯语=[阿拉伯语], 马耳他语=[马耳他语], 阿斯图里亚斯语=[阿斯图里亚斯语], 斯瓦希里语=[斯瓦希里语], 挪威尼诺斯克语=[挪威尼诺斯克语], 梅鲁语=[梅鲁语], 高棉语=[高棉语], 卢奥语=[卢奥语], 台塔语=[台塔语], 拉脱维亚语=[拉脱维亚语], 希伯来文=[希伯来文], 贝纳语=[贝纳语], 瓦尔瑟语=[瓦尔瑟语], 低地德语=[低地德语], 马拉雅拉姆语=[马拉雅拉姆语], 冰岛语=[冰岛语], 维吾尔语=[维吾尔语], 桑戈语=[桑戈语], 温旧语=[温旧语], 印地语=[印地语], 伊博语=[伊博语], 尼泊尔语=[尼泊尔语], 弗留利语=[弗留利语], 印度尼西亚文=[印度尼西亚文], 波兰语=[波兰语], 信德语=[信德语], 马拉加斯语=[马拉加斯语], 依地文=[依地文], 匈牙利语=[匈牙利语], 普什图语=[普什图语], 世界语=[世界语], 希尔哈语=[希尔哈语], 奥塞梯语=[奥塞梯语], 奇加语=[奇加语], 普鲁士语=[普鲁士语], 加利西亚语=[加利西亚语], 恩艮巴语=[恩艮巴语], 威尔士语=[威尔士语], 塔吉克语=[塔吉克语], 索马里语=[索马里语], 葡萄牙语=[葡萄牙语], 西桑海语=[西桑海语], 希腊语=[希腊语], 卡库语=[卡库语], 克丘亚语=[克丘亚语], 博多语=[博多语], 北桑海语=[北桑海语], 意大利语=[意大利语], 爱沙尼亚语=[爱沙尼亚语], 罗马尼亚语=[罗马尼亚语], 林加拉语=[林加拉语], 法语=[法语], 恩甘澎语=[恩甘澎语], 萨哈语=[萨哈语], 古吉拉特语=[古吉拉特语], 加泰罗尼亚语=[加泰罗尼亚语], 约鲁巴语=[约鲁巴语], 卢旺达语=[卢旺达语], 香巴拉语=[香巴拉语], 西班牙语=[西班牙语], 柯尔克孜语=[柯尔克孜语], 荷兰语=[荷兰语], 卡伦金语=[卡伦金语], 卢森堡语=[卢森堡语], 粤语=[粤语], 挪威语=[挪威语], 北卢尔语=[北卢尔语], 土库曼语=[土库曼语], 朗吉语=[朗吉语], 阿肯语=[阿肯语], 老挝语=[老挝语], 芬兰语=[芬兰语], 帕雷语=[帕雷语], 卢雅语=[卢雅语], 宗卡语=[宗卡语], 沃洛夫语=[沃洛夫语], 苏格兰盖尔语=[苏格兰盖尔语], 哲尔马语=[哲尔马语], 瑞典语=[瑞典语], 孟加拉语=[孟加拉语], 藏语=[藏语], 立陶宛语=[立陶宛语], 乌克兰语=[乌克兰语], 卡布佛得鲁语=[卡布佛得鲁语], 斯洛伐克语=[斯洛伐克语], 上索布语=[上索布语]}
    }

    public static Stream<Person> people() {
        return Stream.of(new Person(1001, "A"), new Person(1002, "B"), new Person(1003, "B"));
    }

    public static class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
