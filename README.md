<a id="readme-top"></a>

<!-- PROJECT LOGO & HEADER -->
<br />
<div align="center">
  <h3 align="center">Java Ecosystem Simulation App</h3>
  <p align="center">
    An interactive 2D artificial ecosystem simulation built with Java and Processing. It models predator-prey dynamics, autonomous agent steering behaviors, genetic evolution, and cellular automata terrain generation.
    <br />
    <br />
    <a href="#about-the-project"><strong>Explore the Documentation »</strong></a>
    <br />
    <br />
    <a href="https://github.com/GuilhermeGraca/java-ecosystem-processing/issues">Report Bug</a>
    &middot;
    <a href="https://github.com/GuilhermeGraca/java-ecosystem-processing/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#features--key-highlights">Features & Key Highlights</a></li>
      </ul>
    </li>
    <li><a href="#lessons-learned">Lessons Learned</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation--running-locally">Installation & Running Locally</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

---

<!-- ABOUT THE PROJECT -->
## About The Project

<div align="center">



https://github.com/user-attachments/assets/2b306ed3-0d75-4b8f-bb7f-449d0675382a


  
  <br />
  <p align="center">
    <em>Ecosystem Simulation Demo — Showing cellular automata terrain generation, autonomous agent navigation, and predator-prey dynamics.</em>
    <br />
    <em>If the embedded video above is not displaying correctly, <a href="preview/ecosystemDemo.mp4"><strong>click here to watch/download the video demo »</strong></a></em>
  </p>
</div>

<br />

This repository contains **Java Ecosystem Simulation App**, an academic project developed from scratch in 2024 for the *Modelação e Simulação de Sistemas Naturais* course at **ISEL (Instituto Superior de Engenharia de Lisboa)**, in collaboration with Martim Ramos.

The primary goal of this project is to simulate the life cycle and emergent population balance of predator and prey species in a mixed terrestrial and aquatic environment. The simulation combines cellular automata to generate biomes and renewable food sources with autonomous agents that use sensory vision, physics integration, and genetic mutation to evolve their steering strategies over successive generations.

For a comprehensive academic explanation of the mathematical models, algorithms, and simulation results, please refer to the detailed Portuguese report available in the repository: [`PROJ_FINAL_MSSN_A51736_A51827.pdf`](Relatório/PROJ_FINAL_MSSN_A51736_A51827.pdf).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

### Built With

* [![Java][Java-badge]][Java-url]
* [![Processing][Processing-badge]][Processing-url]
* [![IntelliJ IDEA][IntelliJ-badge]][IntelliJ-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

### Features & Key Highlights

* **Cellular Automata Biomes**: Uses five iterations of a Moore neighborhood majority rule to group cells into realistic biomes of desert, fertile ground, food, and water obstacles.
* **Autonomous Steering Behaviors**: Prey use wander and obstacle avoidance to explore safely, while predators use pursuit steering to intercept the closest prey within their field of vision.
* **Genetic Behavior Mutation**: Offspring inherit survival strategies from parents with slight weight mutations, capping predator pursuit weight to maintain ecological balance.
* **Metabolism and Resource Cycle**: Animals expend energy based on movement speed and obstacle penalties, requiring food patches that regenerate every ten seconds or prey capture to reproduce.
* **Interactive Terrain Control**: Users can click the bottom bar to draw water lakes, food, or empty space directly onto the grid to test population isolation and survival.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<!-- LESSONS LEARNED -->
## Lessons Learned

* **Cellular Automata**: Applying discrete grid states and neighborhood rules to simulate emergent natural landscapes and renewable environmental resources.
* **Autonomous Agent Perception**: Implementing sensory vision cones and steering force vectors so artificial animals detect targets and navigate autonomously.
* **Evolutionary Adaptation**: Modeling genetic inheritance and behavior weight mutation to demonstrate how natural selection favors efficient survival strategies over time.
* **Kinematics and Metabolism**: Integrating physical forces, velocity, and mass with metabolic energy consumption to link mechanical movement directly to survival.
* **Complex Systems Modeling**: Structuring multi-agent interactions and feedback loops to observe emergent population balance between predators and prey.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<!-- GETTING STARTED -->
## Getting Started

Follow these instructions to set up a local copy of the project on your machine.

### Prerequisites

* Java Development Kit 17 or higher
* IntelliJ IDEA or any compatible Java IDE
* Processing 4 library included in the repository root as `core.jar`

### Installation & Running Locally

1. **Clone the repository**:
   ```sh
   git clone https://github.com/GuilhermeGraca/java-ecosystem-processing.git
   ```
2. **Open the project**:
   - Open the cloned directory in IntelliJ IDEA.
3. **Verify Dependencies**:
   - Ensure `core.jar` from the project root is added to your Java module classpath.
4. **Run the Simulation**:
   - Execute the `ProcessingSetup` main class located in the `setup` package to launch the simulation window.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Usage

* **Start Simulation**: Run `ProcessingSetup.java` to start the graphical interface.
* **Edit Terrain**: Click a state button at the bottom of the window for empty space, obstacle, or food, then click on the grid to change cell types.
* **Reset World**: Press the spacebar at any time to regenerate the terrain and reset agent populations.
* **Monitor Statistics**: Check the console output to view real-time data on population size, average speeds, and behavior weights.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Contact

Guilherme Graça — [LinkedIn](https://www.linkedin.com/in/guilhermegraca) — [GitHub](https://github.com/GuilhermeGraca)

Project Link: [https://github.com/GuilhermeGraca/java-ecosystem-processing](https://github.com/GuilhermeGraca/java-ecosystem-processing)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Acknowledgments

* **ISEL (Instituto Superior de Engenharia de Lisboa)** — For the academic environment and resources provided during the Computer Science and Engineering program.
* **Modelação e Simulação de Sistemas Naturais** — Course unit that motivated the development of this simulation project.
* **Martim Ramos** — Co-developer and collaborator on this academic project in 2024.
* [Processing Foundation](https://processing.org/) — For the Processing library used for rendering and visual simulation.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
[Java-badge]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com
[Processing-badge]: https://img.shields.io/badge/Processing-009688?style=for-the-badge&logo=processing&logoColor=white
[Processing-url]: https://processing.org/
[IntelliJ-badge]: https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white
[IntelliJ-url]: https://www.jetbrains.com/idea/
