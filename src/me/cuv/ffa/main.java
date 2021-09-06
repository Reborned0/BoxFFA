
package me.cuv.ffa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
    File f = new File("plugins/FreeForAll/do-not-touch.yml");
    YamlConfiguration yamlFile;
    List<String> ingame;
    List<String> pregame;
    List<String> setup;
    String prefix;
    public static final HashMap<Player, Inventory> gui = new HashMap();

    public main() {
        this.yamlFile = YamlConfiguration.loadConfiguration(this.f);
        this.ingame = new ArrayList();
        this.pregame = new ArrayList();
        this.setup = new ArrayList();
        this.prefix = colorize(this.getConfig().getString("ChatPrefix"));
    }

    public static String colorize(String string) {
        return string.replaceAll("(&([a-fk-or0-9]))", "§$2");
    }

    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
    }

    private void openKits(Player player) {
        if (!gui.containsKey(player)) {
            Inventory inv = Bukkit.createInventory((InventoryHolder)null, 36, colorize(this.getConfig().getString("kitMenuPrefix")));
            gui.put(player, inv);
            String item1 = this.getConfig().getString("kits.kit1.iconID");
            Material i1 = Material.matchMaterial(item1);
            String item2 = this.getConfig().getString("kits.kit1.descID");
            Material i2 = Material.matchMaterial(item2);
            ItemStack firstIcon = new ItemStack(i1);
            ItemStack firstDesc = new ItemStack(i2);
            String item3 = this.getConfig().getString("kits.kit2.iconID");
            Material i3 = Material.matchMaterial(item3);
            String item4 = this.getConfig().getString("kits.kit2.descID");
            Material i4 = Material.matchMaterial(item4);
            ItemStack secondIcon = new ItemStack(i3);
            ItemStack secondDesc = new ItemStack(i4);
            String item5 = this.getConfig().getString("kits.kit3.iconID");
            Material i5 = Material.matchMaterial(item5);
            String item6 = this.getConfig().getString("kits.kit3.descID");
            Material i6 = Material.matchMaterial(item6);
            ItemStack thirdIcon = new ItemStack(i5);
            ItemStack thirdDesc = new ItemStack(i6);
            String item7 = this.getConfig().getString("kits.kit4.iconID");
            Material i7 = Material.matchMaterial(item7);
            String item8 = this.getConfig().getString("kits.kit4.descID");
            Material i8 = Material.matchMaterial(item8);
            ItemStack fourIcon = new ItemStack(i7);
            ItemStack fourDesc = new ItemStack(i8);
            ItemMeta firstIconMeta = firstIcon.getItemMeta();
            ItemMeta firstDescMeta = firstDesc.getItemMeta();
            ItemMeta secondIconMeta = secondIcon.getItemMeta();
            ItemMeta secondDescMeta = secondDesc.getItemMeta();
            ItemMeta thirdIconMeta = thirdIcon.getItemMeta();
            ItemMeta thirdDescMeta = thirdDesc.getItemMeta();
            ItemMeta fourIconMeta = thirdIcon.getItemMeta();
            ItemMeta fourDescMeta = thirdDesc.getItemMeta();
            firstIconMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit1.iconName")));
            firstDescMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit1.descName")));
            ArrayList<String> loreList1 = new ArrayList();
            List<String> lore1 = this.getConfig().getStringList("kits.kit1.descLore");
            Iterator var38 = lore1.iterator();

            while(var38.hasNext()) {
                String s1 = (String)var38.next();
                loreList1.add(colorize(s1));
            }

            firstDescMeta.setLore(loreList1);
            secondIconMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit2.iconName")));
            secondDescMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit2.descName")));
            ArrayList<String> loreList2 = new ArrayList();
            List<String> lore2 = this.getConfig().getStringList("kits.kit2.descLore");
            Iterator var40 = lore2.iterator();

            while(var40.hasNext()) {
                String s2 = (String)var40.next();
                loreList2.add(colorize(s2));
            }

            secondDescMeta.setLore(loreList2);
            thirdIconMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit3.iconName")));
            thirdDescMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit3.descName")));
            ArrayList<String> loreList3 = new ArrayList();
            List<String> lore3 = this.getConfig().getStringList("kits.kit3.descLore");
            Iterator var42 = lore3.iterator();

            while(var42.hasNext()) {
                String s3 = (String)var42.next();
                loreList3.add(colorize(s3));
            }

            thirdDescMeta.setLore(loreList3);
            fourIconMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit4.iconName")));
            fourDescMeta.setDisplayName(colorize(this.getConfig().getString("kits.kit4.descName")));
            ArrayList<String> loreList4 = new ArrayList();
            List<String> lore4 = this.getConfig().getStringList("kits.kit4.descLore");
            Iterator var44 = lore4.iterator();

            while(var44.hasNext()) {
                String s4 = (String)var44.next();
                loreList4.add(colorize(s4));
            }

            fourDescMeta.setLore(loreList4);
            firstIcon.setItemMeta(firstIconMeta);
            firstDesc.setItemMeta(firstDescMeta);
            secondIcon.setItemMeta(secondIconMeta);
            secondDesc.setItemMeta(secondDescMeta);
            thirdIcon.setItemMeta(thirdIconMeta);
            thirdDesc.setItemMeta(thirdDescMeta);
            fourIcon.setItemMeta(fourIconMeta);
            fourDesc.setItemMeta(fourDescMeta);
            int f1 = this.getConfig().getInt("kits.kit1.iconSlotID");
            int f2 = this.getConfig().getInt("kits.kit1.descSlotID");
            inv.setItem(f1, firstIcon);
            inv.setItem(f2, firstDesc);
            int f3 = this.getConfig().getInt("kits.kit2.iconSlotID");
            int f4 = this.getConfig().getInt("kits.kit2.descSlotID");
            inv.setItem(f3, secondIcon);
            inv.setItem(f4, secondDesc);
            int f5 = this.getConfig().getInt("kits.kit3.iconSlotID");
            int f6 = this.getConfig().getInt("kits.kit3.descSlotID");
            inv.setItem(f5, thirdIcon);
            inv.setItem(f6, thirdDesc);
            int f7 = this.getConfig().getInt("kits.kit4.iconSlotID");
            int f8 = this.getConfig().getInt("kits.kit4.descSlotID");
            inv.setItem(f7, fourIcon);
            inv.setItem(f8, fourDesc);
            player.openInventory(inv);
            gui.remove(player);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getInventory().getName().equals(colorize(this.getConfig().getString("kitMenuPrefix")))) {
            int h1 = this.getConfig().getInt("kits.kit1.iconSlotID");
            int h2 = this.getConfig().getInt("kits.kit1.descSlotID");
            int h3 = this.getConfig().getInt("kits.kit2.iconSlotID");
            int h4 = this.getConfig().getInt("kits.kit2.descSlotID");
            int h5 = this.getConfig().getInt("kits.kit3.iconSlotID");
            int h6 = this.getConfig().getInt("kits.kit3.descSlotID");
            int h7 = this.getConfig().getInt("kits.kit4.iconSlotID");
            int h8 = this.getConfig().getInt("kits.kit4.descSlotID");
            Object a;
            Object b;
            ItemStack[] inventory;
            ItemStack[] armor;
            List listb;
            if (event.getRawSlot() == h1 || event.getRawSlot() == h2) {
                event.setCancelled(true);
                a = this.yamlFile.get("kits.kit1.inventory");
                b = this.yamlFile.get("kits.kit1.armor");
                if (a == null || b == null) {
                    player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please set a kit1 with " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set kit1");
                    player.closeInventory();
                    return;
                }

                inventory = null;
                armor = null;
                if (a instanceof ItemStack[]) {
                    inventory = (ItemStack[])a;
                } else if (a instanceof List) {
                    listb = (List)a;
                    inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                if (b instanceof ItemStack[]) {
                    armor = (ItemStack[])b;
                } else if (b instanceof List) {
                    listb = (List)b;
                    armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                player.getInventory().clear();
                player.getInventory().setContents(inventory);
                player.getInventory().setArmorContents(armor);
                player.closeInventory();
                if (this.pregame.contains(player.getName())) {
                    this.pregame.remove(player.getName());
                }
            }

            if (event.getRawSlot() == h3 || event.getRawSlot() == h4) {
                event.setCancelled(true);
                a = this.yamlFile.get("kits.kit2.inventory");
                b = this.yamlFile.get("kits.kit2.armor");
                if (a == null || b == null) {
                    player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please set a kit2 with " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set kit2");
                    player.closeInventory();
                    return;
                }

                inventory = null;
                armor = null;
                if (a instanceof ItemStack[]) {
                    inventory = (ItemStack[])a;
                } else if (a instanceof List) {
                    listb = (List)a;
                    inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                if (b instanceof ItemStack[]) {
                    armor = (ItemStack[])b;
                } else if (b instanceof List) {
                    listb = (List)b;
                    armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                player.getInventory().clear();
                player.getInventory().setContents(inventory);
                player.getInventory().setArmorContents(armor);
                player.closeInventory();
                if (this.pregame.contains(player.getName())) {
                    this.pregame.remove(player.getName());
                }
            }

            if (event.getRawSlot() == h5 || event.getRawSlot() == h6) {
                event.setCancelled(true);
                a = this.yamlFile.get("kits.kit3.inventory");
                b = this.yamlFile.get("kits.kit3.armor");
                if (a == null || b == null) {
                    player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please set a kit3 with " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set kit3");
                    player.closeInventory();
                    return;
                }

                inventory = null;
                armor = null;
                if (a instanceof ItemStack[]) {
                    inventory = (ItemStack[])a;
                } else if (a instanceof List) {
                    listb = (List)a;
                    inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                if (b instanceof ItemStack[]) {
                    armor = (ItemStack[])b;
                } else if (b instanceof List) {
                    listb = (List)b;
                    armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                player.getInventory().clear();
                player.getInventory().setContents(inventory);
                player.getInventory().setArmorContents(armor);
                player.closeInventory();
                if (this.pregame.contains(player.getName())) {
                    this.pregame.remove(player.getName());
                }
            }

            if (event.getRawSlot() == h7 || event.getRawSlot() == h8) {
                event.setCancelled(true);
                a = this.yamlFile.get("kits.kit4.inventory");
                b = this.yamlFile.get("kits.kit4.armor");
                if (a == null || b == null) {
                    player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please set a kit4 with " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set kit4");
                    player.closeInventory();
                    return;
                }

                inventory = null;
                armor = null;
                if (a instanceof ItemStack[]) {
                    inventory = (ItemStack[])a;
                } else if (a instanceof List) {
                    listb = (List)a;
                    inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                if (b instanceof ItemStack[]) {
                    armor = (ItemStack[])b;
                } else if (b instanceof List) {
                    listb = (List)b;
                    armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                }

                player.getInventory().clear();
                player.getInventory().setContents(inventory);
                player.getInventory().setArmorContents(armor);
                player.closeInventory();
                if (this.pregame.contains(player.getName())) {
                    this.pregame.remove(player.getName());
                }
            }

        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (command.getName().equalsIgnoreCase("ffa")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    if (player.isOp()) {
                        player.sendMessage(this.prefix + ChatColor.DARK_AQUA + "FFA Commands:");
                        player.sendMessage(ChatColor.AQUA + "/ffa join");
                        player.sendMessage(ChatColor.AQUA + "/ffa leave");
                        player.sendMessage(ChatColor.AQUA + "/ffa help");
                        player.sendMessage(ChatColor.AQUA + "/ffa setup");
                    } else {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                    }
                }

                Object a;
                Object b;
                ItemStack[] inventory;
                ItemStack[] armor;
                List listb;
                int y;
                int z;
                int yaw;
                int pitch;
                Location loc2;
                int x;
                if (args[0].equalsIgnoreCase("join")) {
                    if (!player.hasPermission("ffa.join") && !player.isOp()) {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                    } else if (this.ingame.contains(player.getName())) {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("AlreadyInFFA")));
                    } else if (!this.getConfig().getBoolean("KitsEnabled")) {
                        try {
                            this.yamlFile.load(this.f);
                        } catch (FileNotFoundException var27) {
                            var27.printStackTrace();
                        } catch (IOException var28) {
                            var28.printStackTrace();
                        } catch (InvalidConfigurationException var29) {
                            var29.printStackTrace();
                        }

                        if (sender instanceof Player) {
                            a = this.yamlFile.get("inv.join.inventory");
                            b = this.yamlFile.get("inv.join.armor");
                            if (a == null || b == null) {
                                player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please use the Setup Wizzard, " + ChatColor.GRAY + ChatColor.BOLD + "/ffa setup");
                                return true;
                            }

                            inventory = null;
                            armor = null;
                            if (a instanceof ItemStack[]) {
                                inventory = (ItemStack[])a;
                            } else if (a instanceof List) {
                                listb = (List)a;
                                inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                            }

                            if (b instanceof ItemStack[]) {
                                armor = (ItemStack[])b;
                            } else if (b instanceof List) {
                                listb = (List)b;
                                armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                            }

                            player.getInventory().clear();
                            player.getInventory().setContents(inventory);
                            player.getInventory().setArmorContents(armor);
                            x = this.yamlFile.getInt("join.x");
                            y = this.yamlFile.getInt("join.y");
                            z = this.yamlFile.getInt("join.z");
                            yaw = this.yamlFile.getInt("join.yaw");
                            pitch = this.yamlFile.getInt("join.pitch");
                            loc2 = new Location(Bukkit.getWorld(this.yamlFile.getString("join.world")), (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, (float)yaw, (float)pitch);
                            player.teleport(loc2);
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("FFAJoinMessage")));
                            player.setGameMode(GameMode.ADVENTURE);
                            this.ingame.add(player.getName());
                        }
                    } else {
                        x = this.yamlFile.getInt("join.x");
                        y = this.yamlFile.getInt("join.y");
                        z = this.yamlFile.getInt("join.z");
                        yaw = this.yamlFile.getInt("join.yaw");
                        x = this.yamlFile.getInt("join.pitch");
                        Location loc = new Location(Bukkit.getWorld(this.yamlFile.getString("join.world")), (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, (float)yaw, (float)x);
                        player.teleport(loc);
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("FFAJoinMessage")));
                        player.setGameMode(GameMode.ADVENTURE);
                        player.getInventory().clear();
                        player.getInventory().setArmorContents((ItemStack[])null);
                        this.pregame.add(player.getName());
                        this.ingame.add(player.getName());
                        ItemStack flint = new ItemStack(Material.FLINT);
                        ItemMeta flintMeta = flint.getItemMeta();
                        flintMeta.setDisplayName("" + ChatColor.GREEN + ChatColor.BOLD + "Open Kit Menu " + ChatColor.GRAY + "(right click)");
                        flint.setItemMeta(flintMeta);
                        player.getInventory().addItem(new ItemStack[]{flint});
                        this.openKits(player);
                    }
                }

                if (args[0].equalsIgnoreCase("leave")) {
                    if (!player.hasPermission("ffa.leave") && !player.isOp()) {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                    } else if (this.ingame.contains(player.getName())) {
                        try {
                            this.yamlFile.load(this.f);
                        } catch (FileNotFoundException var24) {
                            var24.printStackTrace();
                        } catch (IOException var25) {
                            var25.printStackTrace();
                        } catch (InvalidConfigurationException var26) {
                            var26.printStackTrace();
                        }

                        if (sender instanceof Player) {
                            a = this.yamlFile.get("inv.leave.inventory");
                            b = this.yamlFile.get("inv.leave.armor");
                            if (a == null || b == null) {
                                player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Please use the Setup Wizzard, " + ChatColor.GRAY + ChatColor.BOLD + "/ffa setup");
                                return true;
                            }

                            inventory = null;
                            armor = null;
                            if (a instanceof ItemStack[]) {
                                inventory = (ItemStack[])a;
                            } else if (a instanceof List) {
                                listb = (List)a;
                                inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
                            }

                            if (b instanceof ItemStack[]) {
                                armor = (ItemStack[])b;
                            } else if (b instanceof List) {
                                listb = (List)b;
                                armor = (ItemStack[])listb.toArray(new ItemStack[0]);
                            }

                            player.getInventory().clear();
                            player.getInventory().setContents(inventory);
                            player.getInventory().setArmorContents(armor);
                            x = this.yamlFile.getInt("leave.x");
                            y = this.yamlFile.getInt("leave.y");
                            z = this.yamlFile.getInt("leave.z");
                            yaw = this.yamlFile.getInt("leave.yaw");
                            pitch = this.yamlFile.getInt("leave.pitch");
                            loc2 = new Location(Bukkit.getWorld(this.yamlFile.getString("leave.world")), (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, (float)yaw, (float)pitch);
                            player.teleport(loc2);
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("FFALeaveMessage")));
                            player.setGameMode(GameMode.ADVENTURE);
                            this.ingame.remove(player.getName());
                        }
                    } else {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NotInFFA")));
                    }
                }

                if (args[0].equalsIgnoreCase("setup")) {
                    if (player.isOp()) {
                        if (!this.setup.contains(player.getName())) {
                            this.setup.add(player.getName());
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                            player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + " * Started Setup Wizzard");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + "1. " + ChatColor.DARK_GRAY + "First of all we must set the " + ChatColor.UNDERLINE + "Join" + ChatColor.DARK_GRAY + " location," + " to do this you must be in the join location and then type " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set join");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + "2. " + ChatColor.DARK_GRAY + "Next we must set the " + ChatColor.UNDERLINE + "Leave" + ChatColor.DARK_GRAY + " location, to do this you must be in the leave" + " location and then type " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set leave");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + " * Type " + ChatColor.WHITE + ChatColor.BOLD + "/ffa setup next" + ChatColor.GRAY + ChatColor.BOLD + " for the next page.");
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                        } else {
                            player.sendMessage("" + ChatColor.RED + ChatColor.BOLD + " * Setup Wizzard Already Started");
                        }
                    } else {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                    }
                }
            }

            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (args[1].equalsIgnoreCase("join")) {
                        if (!player.hasPermission("ffa.set.join") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("join.world", player.getLocation().getWorld().getName());
                            this.yamlFile.set("join.x", player.getLocation().getBlockX());
                            this.yamlFile.set("join.y", player.getLocation().getBlockY());
                            this.yamlFile.set("join.z", player.getLocation().getBlockZ());
                            this.yamlFile.set("join.yaw", player.getLocation().getYaw());
                            this.yamlFile.set("join.pitch", player.getLocation().getPitch());
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Join Location Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var23) {
                                var23.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("leave")) {
                        if (player.hasPermission("ffa.set.leave")) {
                            this.yamlFile.set("leave.world", player.getLocation().getWorld().getName());
                            this.yamlFile.set("leave.x", player.getLocation().getBlockX());
                            this.yamlFile.set("leave.y", player.getLocation().getBlockY());
                            this.yamlFile.set("leave.z", player.getLocation().getBlockZ());
                            this.yamlFile.set("leave.pitch", player.getLocation().getPitch());
                            this.yamlFile.set("leave.yaw", player.getLocation().getYaw());
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Leave Location Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var22) {
                                var22.printStackTrace();
                            }
                        } else {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        }
                    }

                    if (args[1].equalsIgnoreCase("joininv")) {
                        if (!player.hasPermission("ffa.set.joininv") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("inv.join.inventory", player.getInventory().getContents());
                            this.yamlFile.set("inv.join.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Join Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var21) {
                                var21.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("leaveinv")) {
                        if (!player.hasPermission("ffa.set.leaveinv") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("inv.leave.inventory", player.getInventory().getContents());
                            this.yamlFile.set("inv.leave.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Leave Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var20) {
                                var20.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("kit1")) {
                        if (!player.hasPermission("ffa.set.kits") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("kits.kit1.inventory", player.getInventory().getContents());
                            this.yamlFile.set("kits.kit1.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Kit1 Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var19) {
                                var19.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("kit2")) {
                        if (!player.hasPermission("ffa.set.kits") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("kits.kit2.inventory", player.getInventory().getContents());
                            this.yamlFile.set("kits.kit2.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Kit2 Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var18) {
                                var18.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("kit3")) {
                        if (!player.hasPermission("ffa.set.kits") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("kits.kit3.inventory", player.getInventory().getContents());
                            this.yamlFile.set("kits.kit3.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Kit3 Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var17) {
                                var17.printStackTrace();
                            }
                        }
                    }

                    if (args[1].equalsIgnoreCase("kit4")) {
                        if (!player.hasPermission("ffa.set.kits") && !player.isOp()) {
                            player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                        } else {
                            this.yamlFile.set("kits.kit4.inventory", player.getInventory().getContents());
                            this.yamlFile.set("kits.kit4.armor", player.getInventory().getArmorContents());
                            player.getInventory().clear();
                            player.getInventory().setHelmet((ItemStack)null);
                            player.getInventory().setChestplate((ItemStack)null);
                            player.getInventory().setLeggings((ItemStack)null);
                            player.getInventory().setBoots((ItemStack)null);
                            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + " * Kit4 Inventory Set");

                            try {
                                this.yamlFile.save(this.f);
                            } catch (IOException var16) {
                                var16.printStackTrace();
                            }
                        }
                    }
                }

                if (args[0].equalsIgnoreCase("setup")) {
                    if (player.isOp()) {
                        if (args[1].equalsIgnoreCase("next") && this.setup.contains(player.getName())) {
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                            player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + " * Setup Wizzard Continued");
                            player.sendMessage(ChatColor.DARK_GRAY + "Now we must set the armor for the players when they join.");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + "3. " + ChatColor.DARK_GRAY + "Set your own inventory to how you would like the inventory of a player" + " to be when in the FFA, Then type " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set joininv");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + "4. " + ChatColor.DARK_GRAY + "Now set your own inventory to how you would like the inventory of a player" + " to be when they leave the FFA, Then type " + ChatColor.GRAY + ChatColor.BOLD + "/ffa set leaveinv");
                            player.sendMessage("" + ChatColor.GRAY + ChatColor.BOLD + " * Type " + ChatColor.WHITE + ChatColor.BOLD + "/ffa setup finish" + ChatColor.GRAY + ChatColor.BOLD + " to save and complete all locations and settings.");
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                        }

                        if (args[1].equalsIgnoreCase("finish") && this.setup.contains(player.getName())) {
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                            this.setup.remove(player.getName());
                            player.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + " * Setup Wizzard Complete");
                            player.sendMessage(ChatColor.DARK_GRAY + " Thank you for completeing the setup wizzard");
                            player.sendMessage("           ");
                            player.sendMessage("           ");
                        }
                    } else {
                        player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                    }
                }
            }
        }

        return false;
    }

    @EventHandler
    public void onFalling(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (this.ingame.contains(player.getName())) {
                e.setCancelled(true); // Permet d'enlever les dégats de chute dans la partie
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (this.ingame.contains(player.getName())) {
            player.getInventory().clear();
            int x = this.yamlFile.getInt("join.x");
            int y = this.yamlFile.getInt("join.y");
            int z = this.yamlFile.getInt("join.z");
            int yaw = this.yamlFile.getInt("join.yaw");
            int pitch = this.yamlFile.getInt("join.pitch");
            Location loc = new Location(Bukkit.getWorld(this.yamlFile.getString("join.world")), (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, (float)yaw, (float)pitch);
            event.setRespawnLocation(loc);
            Object a = this.yamlFile.get("inv.join.inventory");
            Object b = this.yamlFile.get("inv.join.armor");
            if (a == null || b == null) {
                player.sendMessage(this.prefix + ChatColor.RED + ChatColor.BOLD + "No Saved Inventory");
                return;
            }

            ItemStack[] inventory = null;
            ItemStack[] armor = null;
            List listb;
            if (a instanceof ItemStack[]) {
                inventory = (ItemStack[])a;
            } else if (a instanceof List) {
                listb = (List)a;
                inventory = (ItemStack[])listb.toArray(new ItemStack[0]);
            }

            if (b instanceof ItemStack[]) {
                armor = (ItemStack[])b;
            } else if (b instanceof List) {
                listb = (List)b;
                armor = (ItemStack[])listb.toArray(new ItemStack[0]);
            }

            player.getInventory().clear();
            player.getInventory().setContents(inventory);
            player.getInventory().setArmorContents(armor);
        }

    }



    @EventHandler
    public void onSignCreation(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (event.getLine(0).equalsIgnoreCase("ffa")) {
            if (event.getLine(1).equalsIgnoreCase("join")) {
                if (!player.hasPermission("ffa.createsign.join") && !player.isOp()) {
                    player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                } else {
                    event.setLine(0, ChatColor.WHITE + "[" + ChatColor.BLUE + "FFA" + ChatColor.WHITE + "]");
                    event.setLine(1, "    ");
                    event.setLine(2, "" + ChatColor.BLACK + ChatColor.BOLD + "Click To");
                    event.setLine(3, "" + ChatColor.BLACK + ChatColor.BOLD + "Join");
                }
            }

            if (event.getLine(1).equalsIgnoreCase("leave")) {
                if (!player.hasPermission("ffa.createsign.leave") && !player.isOp()) {
                    player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                } else {
                    event.setLine(0, ChatColor.WHITE + "[" + ChatColor.BLUE + "FFA" + ChatColor.WHITE + "]");
                    event.setLine(1, "    ");
                    event.setLine(2, "" + ChatColor.BLACK + ChatColor.BOLD + "Click To");
                    event.setLine(3, "" + ChatColor.BLACK + ChatColor.BOLD + "Leave");
                }
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (this.ingame.contains(player.getName())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (this.ingame.contains(player.getName())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && (block.getType() == Material.WALL_SIGN || block.getType() == Material.SIGN_POST)) {
            Sign sign = (Sign)block.getState();
            if (sign.getLine(0).equalsIgnoreCase(ChatColor.WHITE + "[" + ChatColor.BLUE + "ffa" + ChatColor.WHITE + "]") && sign.getLine(2).equalsIgnoreCase("" + ChatColor.BOLD + "click to") && sign.getLine(3).equalsIgnoreCase("" + ChatColor.BOLD + "join")) {
                if (!player.hasPermission("ffa.usesign.join") && !player.isOp()) {
                    player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                } else {
                    player.performCommand("ffa join");
                    player.updateInventory();
                }
            }

            if (sign.getLine(0).equalsIgnoreCase(ChatColor.WHITE + "[" + ChatColor.BLUE + "ffa" + ChatColor.WHITE + "]") && sign.getLine(2).equalsIgnoreCase("" + ChatColor.BOLD + "click to") && sign.getLine(3).equalsIgnoreCase("" + ChatColor.BOLD + "leave")) {
                if (!player.hasPermission("ffa.usesign.leave") && !player.isOp()) {
                    player.sendMessage(this.prefix + colorize(this.getConfig().getString("NoPerms")));
                } else {
                    player.performCommand("ffa leave");
                    player.updateInventory();
                }
            }
        }

        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.FLINT && this.pregame.contains(player.getName()) && this.ingame.contains(player.getName())) {
            this.openKits(player);
        }

    }

}