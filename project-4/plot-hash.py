import pandas as  pd
import matplotlib.pyplot as plt

# read data from analysis.txt
data = pd.read_csv("analysis.txt")

# organize data by order
orders = ["sorted", "shuffled", "reversed"]
colors = {"sorted": "blue", "shuffled": "orange", "reversed": "green"}

for order in orders:
    subset = data[data["order"] == order]
    plt.figure(figsize=(8, 6))

    # plot insertion, search, deletion times
    plt.plot(subset["numLines"], subset["insertTime"], label="Insertion Time", marker='o')
    plt.plot(subset["numLines"], subset["searchTime"], label="Search Time", marker='s')
    plt.plot(subset["numLines"], subset["deleteTime"], label="Deletion Time", marker='^')

    # add labels, title, legend
    plt.xlabel("Number of Lines")
    plt.ylabel("Time (ns)")
    plt.title(f"Running Times for {order.capitalize()} Dataset")
    plt.legend()
    plt.grid(True)
    plt.tight_layout()

    # save plot
    plt.savefig(f"running_times_{order}.png")
    plt.show()