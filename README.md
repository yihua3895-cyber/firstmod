# Firstmod

一个基于 **Minecraft 1.20.1 / Forge** 的模组，加入红宝石、蓝宝石、银矿资源，以及红宝石苹果和红宝石煤炭。包含独立的创造模式物品栏、简体中文与英文翻译，并使用数据生成器维护模型、配方、掉落表和标签。

## 基本信息

| 项目 | 当前配置 |
| --- | --- |
| 模组 ID | `firstmod` |
| 作者 | `apple_tea` |
| 模组版本 | `1.0-SNAPSHOT` |
| Minecraft | `1.20.1` |
| Forge | `47.4.28` |
| Java | JDK 17 |
| Gradle | 8.8 |
| 映射 | Parchment `2023.09.03-1.20.1` |

## 内容与玩法

所有下列 ID 均使用 `firstmod:` 命名空间，例如 `firstmod:ruby`。

| 内容 | ID | 说明 |
| --- | --- | --- |
| 红宝石 | `ruby` | 用于储存块和红宝石苹果 |
| 蓝宝石 | `sapphire` | 可合成蓝宝石块 |
| 银锭 | `silver_ingot` | 可合成银块 |
| 粗银 | `raw_silver` | 银矿掉落物，可熔炼成银锭 |
| 红宝石苹果 | `ruby_apple` | 恢复 10 点饥饿值，饱和度系数为 2.0；满饥饿值时也可食用，获得 60 秒抗性提升 V |
| 红宝石煤炭 | `ruby_coal` | 燃烧时间为 16000 tick，是原版煤炭的 10 倍 |
| 红宝石块 | `ruby_block` | 红宝石储存块 |
| 蓝宝石块 | `sapphire_block` | 蓝宝石储存块 |
| 银块 | `silver_block` | 银锭储存块 |

### 矿石采集

| 矿石 | ID | 最低采集工具 | 掉落规则 |
| --- | --- | --- | --- |
| 红宝石矿石 | `ruby_ore` | 铁镐 | 通常掉落 1 个红宝石；时运可增加数量，精准采集掉落矿石自身 |
| 蓝宝石矿石 | `sapphire_ore` | 铁镐 | 固定掉落 1 个蓝宝石；当前实现中时运和精准采集都不改变掉落 |
| 银矿石 | `silver_ore` | 石镐 | 基础掉落 2～5 个粗银；时运可增加数量，精准采集掉落矿石自身 |

当前源码未配置矿石自然生成，矿石可通过创造模式 **Firstmod** 标签页或命令获取。红宝石煤炭目前没有合成配方。项目还保留了开发示例方块 `example_block`。

```mcfunction
/give @p firstmod:ruby 64
/give @p firstmod:ruby_ore 16
/give @p firstmod:ruby_apple 1
/give @p firstmod:ruby_coal 1
```

### 合成与熔炼

- 9 个红宝石、蓝宝石或银锭，可以在工作台合成对应储存块；每个储存块可拆回 9 个对应材料。
- 8 个红宝石围绕 1 个苹果，合成 1 个红宝石苹果。

| 原料 → 产物 | 设备 | 时间 | 经验 |
| --- | --- | --- | --- |
| 粗银 → 银锭 | 熔炉 | 200 tick（10 秒） | 0.7 |
| 粗银 → 银锭 | 高炉 | 100 tick（5 秒） | 0.7 |
| 银矿石 → 银锭 | 高炉 | 100 tick（5 秒） | 0.7 |
| 红宝石矿石 → 红宝石 | 高炉 | 100 tick（5 秒） | 1.0 |
| 蓝宝石矿石 → 蓝宝石 | 高炉 | 100 tick（5 秒） | 1.0 |

每次熔炼产出 1 个物品，秒数按正常 20 TPS 计算。

## 安装

1. 准备 Minecraft **1.20.1** 和 Forge **47.4.28** 环境，使用 Java 17。
2. 将构建得到的 `firstmod-1.0-SNAPSHOT.jar` 放入该游戏实例的 `mods` 文件夹。
3. 启动游戏，在模组列表中查看 Firstmod，并在创造模式的 **Firstmod** 标签页查看物品。

当前构建配置没有声明其他必需的第三方模组依赖。

## 开发与构建

在项目根目录使用 PowerShell 执行以下命令。当前仓库缺少 `gradle/wrapper/gradle-wrapper.jar`，因此直接运行 `gradlew.bat` 会失败；以下命令以已经安装并加入 PATH 的 **Gradle 8.8** 为前提。

```powershell
# 生成语言、模型、配方、掉落表和标签
gradle runData

# 编译并打包
gradle build

# 启动开发客户端
gradle runClient
```

构建产物位于：

```text
build/libs/firstmod-1.0-SNAPSHOT.jar
```

若 Gradle 未加入 PATH，可以使用 Gradle 8.8 安装目录下 `bin/gradle.bat` 的完整路径执行。首次构建需要下载依赖；只有依赖已完整缓存时才适合添加 `--offline`。

### 数据生成与翻译

修改数据生成器后先运行 `runData`，再执行 `build`。构建配置会将 `src/generated/resources` 一并打包；单独执行 `build` 不会自动更新生成的数据。

- 简体中文名称：[ModZhCnLanguageProvider.java](src/main/java/com/abc/firstmod/firstmod/datagen/ModZhCnLanguageProvider.java)
- 英文名称：[ModEnUsLanguageProvider.java](src/main/java/com/abc/firstmod/firstmod/datagen/ModEnUsLanguageProvider.java)
- 配方：[ModRecipeProvider.java](src/main/java/com/abc/firstmod/firstmod/datagen/ModRecipeProvider.java)
- 掉落规则：[ModBlockLootTablesProvider.java](src/main/java/com/abc/firstmod/firstmod/datagen/ModBlockLootTablesProvider.java)

请修改对应的 Java Provider，再重新生成资源。直接修改生成的 JSON 会在下次 `runData` 时被覆盖。

## 项目结构

```text
src/main/java/com/abc/firstmod/firstmod/
├── Firstmod.java          # 模组入口
├── ModItems.java          # 物品注册
├── ModBlocks.java         # 方块注册
├── ModCreativeTab.java    # 创造模式标签页
├── ModFoods.java          # 食物属性
├── ModFuel.java           # 燃料物品
├── Config.java            # 配置定义
└── datagen/               # 数据生成器
src/main/resources/        # 纹理、模组元数据、资源来源与许可
src/generated/resources/   # 自动生成的资源与数据
```

## 资源来源与许可

模组元数据中的许可为 **All Rights Reserved（保留所有权利）**。部分资源来源于 TechReborn，并随项目保留其 MIT 许可与来源说明；第三方资源遵循各自的许可。

- [纹理与模型来源说明](src/main/resources/META-INF/TEXTURE-CREDITS.txt)
- [TechReborn MIT 许可](src/main/resources/META-INF/licenses/TechReborn-MIT.txt)

本文依据当前源码与资源配置编写；功能描述不代表已经完成全部游戏内测试。
