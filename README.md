# electrum-checker

Electrum 2 model checker and a small OCL-invariant-to-Electrum translator. The translator can use either an existing Electrum model or a UML 2 XMI model as its type context.

## Build the image

```bash
docker build -f Dockerfile -t electrumchecker:latest .
```

## Translate OCL invariants

The converter parses one OCL `context` and one or more `inv` declarations, then generates Electrum assertions and bounded `check` commands. Select exactly one type context: an existing `.ele` model with `--model`, or UML 2 XMI with `--uml`.

The initial UML profile supports named classes, enumerations and class-owned properties (including navigable association ends) with multiplicities `1`, `0..1`, `0..*` and `1..*`. UML `Integer` maps to Electrum `Int`; references to classes and enumerations map to Electrum relations. Generalization, association-owned ends, UML operations and other UML datatypes are not yet supported. The OCL grammar supports Boolean and integer literals, `self` navigation, comparisons, arithmetic, and Boolean operators; it does not yet implement collections, `let`, operation contracts, `null`, or `invalid`.

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

### Generate Electrum directly from UML XMI

[`models/ascensor-papyrus/ascensor.uml`](models/ascensor-papyrus/ascensor.uml) is a Papyrus UML 2 XMI model with a class, enumerations and scalar properties. Translate it together with the same OCL invariant:

```bash
docker run --rm -v "$PWD:/workspace" --entrypoint ocl2electrum electrumchecker:latest \
	--uml /workspace/models/ascensor-papyrus/ascensor.uml \
	--ocl /workspace/models/ascensor.ocl \
	--out /workspace/models/ascensor_desde_uml.ele \
	--steps 5
```

Then check the generated file (it contains one check, command index `0`):

```bash
docker run --rm -v "$PWD/models:/models:ro" electrumchecker:latest \
	--command 0 /models/ascensor_desde_uml.ele
```

### Open the elevator model in Papyrus

[`models/ascensor-papyrus/`](models/ascensor-papyrus) contains an Eclipse project with the Papyrus resources `ascensor.di`, `ascensor.notation`, and `ascensor.uml`. In Eclipse, import `models/ascensor-papyrus` using **File > Import > Existing Projects into Workspace**, then open `ascensor.di` to view the class diagram. The `.uml` resource can also be passed directly to `--uml` in the converter.
