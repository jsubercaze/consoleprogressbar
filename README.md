# Console progress bar

A progress bar in the console. The task is divided in subtasks, when you update the number of completed subtasks, the bar, percentage and ETA are automatically updated.

Customization is accessible through the setters:
1. Characters for the bar
2. Width of the bar
3. Start and end characters for the bar

## Known

This console progress bar doesn't work in Eclipse, this is a [known bug](https://bugs.eclipse.org/bugs/show_bug.cgi?id=76936) in Eclipse.

## Sample Usage

```java
ConsoleProgressBar bar = new ConsoleProgressBar(tasks);
bar.startAndDisplay();
for (int i = 0; i < tasks; i += increment) {
 bar.updateAndDisplay(increment);
 Thread.sleep(minrandomTime + rand.nextInt(maxrandomTime - minrandomTime));
}
```
