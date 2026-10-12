# electrum-checker

Electrum 2 model checker and a small OCL-invariant-to-Electrum translator.

## Build the image

```bash
docker build -f Dockerfile -t electrumchecker:latest .
```

## Translate OCL invariants

The converter takes an existing `.ele` model as its type context, parses one OCL `context` and one or more `inv` declarations, then appends Electrum assertions and bounded `check` commands. The current grammar supports Boolean and integer literals, `self` navigation, comparisons, arithmetic, and Boolean operators. It does not yet implement OCL collections, `let`, operation contracts, `null`, or `invalid`; type correctness of properties is checked by Electrum when the generated model is run.

Example input, [`models/ascensor.ocl`](models/ascensor.ocl), targets the `Ascensor` signature defined by [`models/ascensor_contraejemplo.ele`](models/ascensor_contraejemplo.ele):

```bash
docker run --rm -v "$PWD:/workspace" --entrypoint ocl2electrum electrumchecker:latest \
	--model /workspace/models/ascensor_contraejemplo.ele \
	--ocl /workspace/models/ascensor.ocl \
	--out /workspace/models/ascensor_generado.ele \
	--steps 5
```

Each OCL invariant becomes an Electrum assertion quantified over its context and wrapped in `always`. The converter appends a `check` command for each assertion. In this example the base model already has one `check`, so the generated check has index `1`:

```bash
docker run --rm -v "$PWD/models:/models:ro" electrumchecker:latest \
	--command 1 /models/ascensor_generado.ele
```

The generated invariant should find the elevator counterexample in the intentionally unsafe model. Use a corrected `.ele` model as `--model` to check a repaired design.
