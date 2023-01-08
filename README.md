# README

Chisel 工程模板， 示例模块来自于 [chisel-template](https://github.com/freechipsproject/chisel-template) 。

## 目录结构

- `build.sbt` SBT 工程定义（请勿修改！）
- `src/main/scala` 工程代码 (模块) 目录
- `src/test/scala` 测试代码 (仿真) 目录

## 生成 Verilog

```bash
sbt "runMain GCD"
```

Verilog 代码生成在 `output_dir` （由 `emitVerilog` 的 `--target-dir` 参数指定）。

## 运行仿真

```bash
sbt "testOnly GCDTest"
```

仿真文件生成在 `test_run_dir` 内以 scalatest name 名称命名的子目录。

## Verdi 查看波形

```bash
cd test_run_dir/gcd
verdi -ssf gcd.fsdb
```
