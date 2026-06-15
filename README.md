# MRI Demo – Interview Project Analysis

## Introduction

This repository contains a review and technical analysis of the **MRI Demo** project, prepared as part of a **2026 job interview process**.

The purpose of this project was not primarily to implement a new feature set, but to inspect, understand, and evaluate an existing Java-based MRI acquisition demo application from architectural, maintainability, testability, and user-experience perspectives.

The analysis was performed in 2026 and focuses on identifying the strengths, risks, bottlenecks, and possible improvement directions of the received project.

---

## Description

The project represents a simplified MRI acquisition workflow.

At a high level, the application allows the user to:

- select an MRI sequence through a Swing-based GUI
- configure acquisition parameters
- send sequence events to a simulated hardware component
- receive generated measurement data through socket communication
- process the acquired data with processing plugins
- visualize the measured and processed data in a simple graphical window

The project contains a hardware simulator, a hardware driver, sequence definitions, parameter handling, acquisition orchestration, processing plugins, and Swing-based user interface components.

A detailed technical review can be found in the [REVIEW.md](./REVIEW.md) file located in the root directory.

---

## 🚀 Assignment Context

This repository was created in connection with a **2026 interview assignment**.

The goal of the assignment was to review the existing MRI Demo project and provide a structured technical analysis of its current state.

The review focuses on the following areas:

1. Architecture
2. Efficiency
3. Optimizability
4. Maintainability
5. Clean Code Practices
6. Generalizability
7. Testability
8. User Experience
9. Summary of recommended improvements

The final analysis is documented in [REVIEW.md](./REVIEW.md).

---

## 🚀 Setup & Running

### Prerequisites

Before running the application, make sure the following tools are available:

- Java 17 or newer
  - The Maven configuration currently targets Java 17.
  - The project was reviewed in an environment using Java 26.
- Maven
- Git
- An IDE such as IntelliJ IDEA is recommended

No database, Docker, Kafka, or external service is required.

The application uses:

- Java
- Maven
- Swing
- Socket-based local communication
- A local `instrument.txt` configuration file

---

### Clone the Repository

```bash
git clone <repository-url>
cd MRI-Demo
```

---

### Build the Project

Using Maven:

```bash
mvn clean compile
```

If a Maven wrapper is added later, the equivalent commands would be:

```bash
./mvnw clean compile
```

On Windows:

```bash
mvnw.cmd clean compile
```

---

### Running the Application

The main entry point is:

```text
com.mediso.Main
```

Using Maven, if the project is configured with an execution plugin:

```bash
mvn exec:java -Dexec.mainClass="com.mediso.Main"
```

Alternatively, the application can be started directly from an IDE by running the `main` method in:

```text
src/main/java/com/mediso/Main.java
```

When started, the application opens the main Swing window where an MRI sequence can be selected.

---

### Runtime Configuration

The hardware simulator reads its configuration from:

```text
instrument.txt
```

Example values include:

```text
model=PET/MRI 3T
baselineAmplitude=-1.5
NoiseAmplitude=0.1
gaussianAmplitude=5.0
gaussianSigma=5.0
delayMultiplier=3000.0
```

This file is used by the simulated instrument to configure generated measurement data and acquisition timing behavior.

---

## 🧭 Application Flow

The typical runtime flow is:

1. The application starts a local `HardwareSimulator` on port `8080`.
2. The main Swing window is displayed.
3. The user selects a sequence: `Gradient Echo` or `Spin Echo`
4. The acquisition manager window opens.
5. The user can inspect and modify parameters.
6. The sequence diagram can be displayed.
7. The acquisition can be started.
8. The `HardwareDriver` connects to the simulator through a socket.
9. Sequence events are sent to the simulator.
10. Simulated measurement data is received.
11. Processing plugins are executed.
12. The resulting data is shown in the data viewer window.

---

## 🧰 Tech Stack

- Java
- Maven
- Swing
- Java Socket API
- Object streams
- Custom hardware simulation
- Custom sequence model
- Custom processing plugin abstraction

---

## 🧠 Thought Process

The analysis was performed by reading through the project structure and identifying the main responsibilities of each component.

The project demonstrates a useful high-level concept:

> Define sequences, send events to hardware, receive measurement data, and process it through plugins.

The review was not limited to whether the application can run. It also considered how the project could evolve if it were moved from a demo state toward a more maintainable, testable, configurable, and production-like structure.

The main focus areas were:

- separation of responsibilities
- UI and business-logic coupling
- socket and resource lifecycle handling
- data processing design
- configurability
- testability
- clean code consistency
- extensibility of sequence and plugin abstractions
- user experience of the Swing interface

---

## 📌 Analysis Summary

### Architecture

The application has a clear conceptual structure around MRI acquisition:

- `Sequence` defines the measurement logic.
- `HardwareDriver` communicates with the simulator.
- `HardwareSimulator` generates measurement data.
- `AcquisitionManager` coordinates acquisition and processing.
- `ProcessPlugin` represents a processing step.
- Swing classes provide the user interface.

>The architecture is understandable for a demo project, but several responsibilities are tightly coupled. The most important architectural concern is that acquisition, GUI handling, processing, and concrete hardware communication are strongly connected in the current implementation.

---

### Efficiency

The current efficiency is acceptable for a demonstration project, but there are several bottlenecks and risks:

- `Sequence.getEvents()` reconstructs a new list of `SequenceEvent` objects every time it is called.
- The smoothing logic in `AverageAndSlidingWindowAndRescale` recalculates window sums instead of using a rolling sum.
- Socket communication is synchronous and blocking.
- Acquisition is started directly from the GUI workflow, which can block the Swing Event Dispatch Thread.
- `HardwareDriver.stopConnection()` exists but is not called, which can lead to resource lifecycle issues.

> The most important efficiency-related improvement would be to separate long-running acquisition work from the UI thread.

---

### Optimizability

The project has some useful extension points, especially the `Sequence` and the `ProcessPlugin`.
However, practical optimization is limited by hard-coded values and direct dependency construction.

Examples:

- sample size `128` is hard-coded in multiple places
- host and port are hard-coded
- processing plugins are instantiated directly in `AcquisitionManager`
- instrument configuration is read from a fixed relative path
- sequence events are represented through multiple parallel collections

> A more configurable setup would make the project easier to optimize and adapt.

---

### Maintainability

The maintainability of the project is moderate for a demo application.

The code is readable in many places, but the following areas would benefit from improvement:

- clearer ownership boundaries
- dependency injection instead of direct object creation
- stronger resource management
- consistent error handling
- composition over inheritance in the data model
- stronger parameter registry instead of name-based lookup and casting
- consistent naming conventions
- logging framework usage instead of direct printing or swallowed exceptions

> One notable design issue is that `DataSet` extends `Header`, while conceptually a data set should probably have a header rather than be a header.

---

### Clean Code Practices

The project is understandable, but not consistently clean.

The most visible clean-code concerns are:

- over-composed processing logic in `AverageAndSlidingWindowAndRescale`
- magic numbers
- inconsistent validation behavior
- exposed mutable internal data
- redundant comments
- silent exception handling
- inconsistent field naming
- unused or ineffective configuration flags, such as `canBeEmpty`

> A cleaner structure would split multi-step processing into smaller named operations and centralize validation rules.

---

### Generalizability

The project contains early signs of generalization, but the implementation is still specific to:

- one simulator workflow
- one socket-based communication approach
- one GUI workflow
- one fixed acquisition data shape
- one hard-coded processing chain

The `Sequence` abstraction and the `ProcessPlugin` interface are good starting points, but they could be made more flexible.

> Potential improvements include:
> - a more compact sequence event model
> - configurable processing pipeline
> - generalized parameter metadata
> - plugin registry
> - abstraction over hardware communication
> - abstraction over the display layer

---

### Testability

Testability is currently weak to moderate.

Some classes can be tested in isolation, for example:

- `NumericParam`
- `NumericListParam`
- `TextParam`
- `SequenceItem`
- individual processing plugins

However, many important workflows are challenging to test because they depend directly on:

- Swing UI classes
- sockets
- fixed files
- threads
- static orchestration
- concrete simulator and driver classes

> The most important testability improvements would be:
> - inject hardware dependencies
> - inject display dependencies
> - externalize configuration
> - avoid static orchestration
> - return results from workflows
> - make processing functions pure where possible
> - add unit tests for processing and parameter validation

---

### User Experience

The graphical interface is minimal and functional, but visually dated.

The current UI allows the user to:

- select a sequence
- open the acquisition manager
- edit parameter values
- display the sequence
- start acquisition
- view acquired and processed data

Potential UX improvements:

- better alignment and spacing
- clearer visual grouping of form fields
- save all parameter changes at once instead of one button per field
- visual feedback after saving changes
- better distinction between graph data and status messages
- grid lines or axes in the data viewer
- improved readability in the sequence view

> The current interface works as a demo, but could be made more user-friendly with relatively small changes.

---

## 🔧 Possible Future Improvements

Recommended future improvements include:

- separate data acquisition from the Swing Event Dispatch Thread
- close socket resources reliably in `HardwareDriver`
- introduce `AutoCloseable` or try-with-resources for hardware communication
- externalize magic numbers and runtime configuration
- make port, host, sample size, correction values, and window sizes configurable
- store `SequenceEvent` objects directly instead of reconstructing them from parallel lists
- introduce dependency injection
- decouple UI, acquisition, hardware communication, and processing
- introduce a logging framework
- improve error handling and avoid swallowed exceptions
- replace name-based parameter lookup with a safer typed registry
- make processing plugins configurable
- add unit tests around parameters, sequences, plugins, and instrument parsing
- improve Swing UX with clearer layout and feedback
- consider a model/controller split for GUI-related logic

---

## 🧪 Suggested Test Targets

Priority test targets identified during the review:

1. `NumericParam`: clamping behavior, boundary values
2. `NumericListParam`: length validation, min/max value handling, mutation behavior
3. `TextParam`: empty string behavior,`canBeEmpty` handling
4. `GradientEcho` and `SpinEcho`: generated event counts, sequence structure
5. `BaselineCorrection`: baseline subtraction, mutation behavior
6. `AverageAndSlidingWindowAndRescale`: average calculation, smoothing behavior, rescaling behavior, edge cases with short or empty input
7. `Instrument`: parsing configuration values, saving configuration, behavior with missing or malformed files after refactoring

---

## 📄 Review Document

This document contains the detailed analysis that serves as the main deliverable of the interview-related review work. The full review is available here: [REVIEW.md](./REVIEW.md)

---

## 📄 License

This repository was created as part of an interview-related technical analysis in 2026.

The code and review are provided for portfolio and reference purposes.
Please do not reuse this work as a direct submission for the same or a similar assignment.

---

_Submitted as part of Mediso interview project analysis – kyussfia – 2026_