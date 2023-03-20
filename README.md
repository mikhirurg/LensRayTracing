# LensRayTracing

## Project description

Ray tracing and rays trajectory modeling for the cylindrical lens and single light source.
This project allows to simulate the ray tracing for a simple configuration of one cylindrical lens and one light source, user can dynamically change various parameters of the simulation scheme and obtain different ray trajectories.

## Installation guide
The project requires Java version 13+ in order to run the simulation. 
There are a few different ways how a user can obtain the jar file to execute the project:
- The latest stable version of the project is available in the [Releases](https://github.com/mikhirurg/LensRayTracing/releases) section of the repository
- The user can build the executable jar from sources by using the script ```build.sh```

After obtaining the executable jar, user can just run the following command:

```
java -jar LensRayTracing*.jar
```

## How to use the project

On the right side of the application, there is a simulation zone where the user can see the cylindrical lens, light source (shown as a blue cube), and ray trajectories. The user can rotate the 3d scene with a mouse to observe the simulation from different angles. On the right side of the application window there is a panel with the controls of the physics scene. The user can set the position of the light source and change some of the parameters of the cylindrical lens (radius, height, depth).

<img src="/img/interface.png" alt="Interface" width=800>

Additionally, the simulation supports the keyboard controls to change the light source position:
- `W`/`S` to move the source forward/backward
- `A`/`D` to move the source to the left/to the right
- `R`/`F` to move the source up/down
- `Space` to reset the view

<img src="/img/demo.gif" alt="Interface" width=600>

## Examples

| Configuration | Simulation |
|---------------|------------|
| X=0.0, Y=0.0, Z=480.0, Radius=300.0, Height=120.0, D=60.0      | <img src="/img/demo_01.png" alt="Demo 1" width=400> |
| X=0.0, Y=0.0, Z=480.0, Radius=150.0, Height=120.0, D=60.0      | <img src="/img/demo_02.png" alt="Demo 1" width=400> |
| X=0.0, Y=0.0, Z=480.0, Radius=50.0, Height=120.0, D=60.0      | <img src="/img/demo_03.png" alt="Demo 1" width=400> |

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## License

[MIT](/LICENSE.txt)
